import java.rmi.NoSuchObjectException;
import java.util.Date;

public record Auto(String nazev, float cena, Pohon pohonVozidla,  int pocetDveri, Date datumVyroby,Uzivatel majitel,String popis) implements Comparable<Auto>{
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Auto auto = (Auto) o;
		return nazev.equals(auto.nazev);
	}

	@Override
	public int hashCode() {
		return nazev.hashCode();
	}

	public Auto{
		if (nazev == null) {
			throw new IllegalArgumentException("Nazev nemuze byt NULL");
		}
		if(nazev.length()<3){
			throw new IllegalArgumentException("Nazev nemuze byt mene nez 3 znaky");
		}
		if (pohonVozidla == null) {
			throw new IllegalArgumentException("Pohon Auta nemuze byt NULL");
		}
		if (cena < 0) {
			throw new IllegalArgumentException("Cena Auta nemuze bit zaporna");
		}
		if (pocetDveri < 0) {
			throw new IllegalArgumentException("Auto nemuze mit zaporny pocet dveri");
		}
		if(datumVyroby==null) {
			throw new IllegalArgumentException("Datum vyroby nemuze byt NULL");
		}
		if(majitel==null){
			throw new IllegalArgumentException("Majitel polozky nemuze byt null");
		}
		if(!DatabazeUzivatelu.getInstance().getUzivatel(majitel.getUserName()).equals(majitel)){
			throw new IllegalArgumentException("Majitel musi byt v databazi a musi byt upToDate");
		}
		if(popis==null){
			throw new IllegalArgumentException("Popis polozky nemuze byt null");
		}
		for (String info:majitel.getPersonalInfo()) {
			if(popis.contains(info)){
				throw new IllegalArgumentException("Popis nemuze obsahovat osobni udaje Majitele");
			}
		}
	}

	@Override
	public int compareTo(Auto o) {
		return o.nazev.compareTo(this.nazev);
	}
}
