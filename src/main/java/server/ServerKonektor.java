package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class ServerKonektor implements Runnable {
    private final ServerSocket serverSocket;
    private final int maxNumberOfClients;
    private final HashMap<ServerKlient, Thread> klients;
    private final Queue<ServerKlient> socketQueue;

    public ServerKonektor(int portNumber, int numberOfClients) throws IOException {
        socketQueue = new ArrayDeque<>();
        maxNumberOfClients = numberOfClients;
        klients = new HashMap<>();
        serverSocket = new ServerSocket(portNumber);
        run();
    }

    @Override
    public void run() {
        while (true) {
            if (serverSocket != null) {
                System.out.println("SERVER: Waiting for new client");
                try {
                    connectNewClient(serverSocket.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }

    private void connectNewClient(Socket socket) {
        if (socket != null) {
            if (klients.size() < maxNumberOfClients) {
                System.out.println("SERVER: NEW klient connected (" + socket.getRemoteSocketAddress() + ")");
                ServerKlient klient = new ServerKlient(socket, this,false);
                Thread klientThread = new Thread(klient);
                klients.put(klient, klientThread);
                klientThread.start();
            } else {
                System.out.println("SERVER: List is full putting client to queue");
                socketQueue.add(new ServerKlient(socket, this,true));
            }
        }else{
            System.out.println("SERVER: error null socket");
        }
    }

    public void remove(ServerKlient serverKlient) {
        System.out.println("SERVER: removed klient("+serverKlient.getId()+") with IP:"+serverKlient.remoteIpAddress());
        klients.remove(serverKlient);
        if (socketQueue.size() > 0) {
            ServerKlient klient=socketQueue.poll();
            System.out.println("SERVER: NEW klient connected (" + klient.remoteIpAddress() + ")");
            Thread klientThread = new Thread(klient);
            klients.put(klient, klientThread);
            klientThread.start();
        }
    }
}
