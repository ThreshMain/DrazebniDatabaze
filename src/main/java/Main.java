import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        try {
            URL resource = Main.class.getClassLoader().getResource("inzerat.txt");
            if (resource != null) {
                System.out.println("resource = " + resource.toURI());
                InseratParser parser=new InseratParser(new File(resource.toURI()));
                System.out.println("parser.getEntita() = " + parser.getEntita());
                System.out.println("parser.getProdavajici() = " + parser.getProdavajici());
            }

            Uzivatel martin = new Uzivatel("kolobozka6b", "martinkos007@gmail.com", "Heslo1234");
            martin.setJmeno("Martina");
//            martin.setHeslo("Heslo1234");
            martin.setAdresa("Praha 7,17000");
//            martin.setMail("martinkos007@gmail.com");
            martin.setTelefon("735986705");
//            martin.setUserName("Kolobozka6b");
            DatabazeUzivatelu.getInstance().addUzivatele(martin);
            System.out.println(DatabazeUzivatelu.getInstance().addUzivatele(martin));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse("12-12-2012");
            long mills = date.getTime();
            Auto a = new Auto("Super rychle auto", 110.5f, Pohon.BENZIN, 2, new Date(mills), martin, "getCastka\nPopis tohoto super rychleho auta\nJe proste super rychle a mega levne.\n");
            DrazebniDatabaze data = DrazebniDatabaze.getInstance();
            data.pridej(a);
            data.zacniDrazbu("Super rychle auto");
            data.getAktualniDrazba().prihod(new Nabidka(martin, 115.2f));
            System.out.println("DrazebniDatabaze.getInstance().getAktualniDrazba() = " + DrazebniDatabaze.getInstance().getAktualniDrazba());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
