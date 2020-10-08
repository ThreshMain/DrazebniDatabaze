import java.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Uzivatel martin=new Uzivatel();
			martin.setJmeno("Martina");
			martin.setHeslo("Heslo1234");
			martin.setAdresa("Praha 7,17000");
			martin.setMail("martinkos007@gmail.com");
			martin.setTelefon("735986705");
			martin.setUserName("Kolobozka6b");
			DatabazeUzivatelu.getInstance().AddUzivatele(martin);
			System.out.println(DatabazeUzivatelu.getInstance().AddUzivatele(martin));		;
			Auto a=new Auto("Super rychle auto",110.5f,Pohon.BENZIN,2, new Date(92,Calendar.FEBRUARY,2),martin, """
					Popis tohoto super rychleho auta
					Je proste super rychle a mega levne.
					""");
			DrazebniDatabaze data=DrazebniDatabaze.getInstance();
			data.Pridej(a);
			data.ZacniDrazbu("Super rychle auto");
			data.getAktualniDrazba().Prihod(new Nabidka(martin,115.2f));
			System.out.println("DrazebniDatabaze.getInstance().getAktualniDrazba() = " + DrazebniDatabaze.getInstance().getAktualniDrazba());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
