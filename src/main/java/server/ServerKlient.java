package server;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

public class ServerKlient implements Runnable,Closeable {
    private final Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private final int id;

    private SocketAddress remoteIp;

    private final ServerKonektor server;

    private boolean inQueue;

    public ServerKlient(Socket socket,ServerKonektor server,boolean inQueue) {
        this.id=this.hashCode();
        this.socket=socket;
        this.server=server;
        this.inQueue=inQueue;
        try {
            if(!inQueue){
                InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                input = new BufferedReader(reader);
            }
            output = new PrintWriter(socket.getOutputStream(), true);
            remoteIp=socket.getRemoteSocketAddress();
            if(inQueue){
                output.println("You are in QUEUE wait to get started");
            }else{
                output.println("Welcome you can now use commands");
            }
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public SocketAddress remoteIpAddress() {
        return remoteIp;
    }

    @Override
    public void run() {
        if(inQueue){
            inQueue=false;
            output.println("Welcome you can now use commands");
            try {
                long skipedNumberOfChars=socket.getInputStream().skip(socket.getInputStream().available());
                System.out.println("SERVER-KLIENT("+id+"): skipped "+skipedNumberOfChars+" klient was in queue");
                InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                input = new BufferedReader(reader);
            } catch (IOException e) {
                close();
                e.printStackTrace();
            }
        }
        while (true) {
            String inputString;
            try {
                inputString = input.readLine();
                if (inputString == null) {
                    close();
                    break;
                }
                System.out.println("SERVER-KLIENT("+id+"): "+inputString);
                output.println("Hi: " + new Date() + " You said: " + inputString);
            } catch (IOException e) {
                close();
                e.printStackTrace();
                break;
            }
        }
    }

    @Override
    public void close() {
        if(socket!=null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.remove(this);
    }
}
