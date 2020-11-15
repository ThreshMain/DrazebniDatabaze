import java.util.Date;

public class Auto implements Comparable<Auto> {
    private String nazev;
    private float cena;
    private Pohon pohonVozidla;
    private int pocetDveri;
    private Date datumVyroby;


    public String getNazev() {
        return nazev;
    }

    public float getCena() {
        return cena;
    }

    public Pohon getPohonVozidla() {
        return pohonVozidla;
    }

    public int getPocetDveri() {
        return pocetDveri;
    }

    public Date getDatumVyroby() {
        return datumVyroby;
    }

    public Uzivatel getMajitel() {
        return majitel;
    }

    public String getPopis() {
        return popis;
    }

    private Uzivatel majitel;
    private String popis;

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

    public void setNazev(String nazev) {
        if (nazev == null) {
            throw new IllegalArgumentException("Nazev nemuze byt NULL");
        }
        if (nazev.length() < 3) {
            throw new IllegalArgumentException("Nazev nemuze byt mene nez 3 znaky");
        }
        this.nazev = nazev;
    }

    public void setCena(float cena) {
        if (cena < 0) {
            throw new IllegalArgumentException("Cena Auta nemuze bit zaporna");
        }
        this.cena = cena;
    }

    public void setPohonVozidla(Pohon pohonVozidla) {
        if (pohonVozidla == null) {
            throw new IllegalArgumentException("Pohon Auta nemuze byt NULL");
        }
        this.pohonVozidla = pohonVozidla;
    }

    public void setPocetDveri(int pocetDveri) {
        if (pocetDveri < 0) {
            throw new IllegalArgumentException("Auto nemuze mit zaporny pocet dveri");
        }
        this.pocetDveri = pocetDveri;
    }

    public void setDatumVyroby(Date datumVyroby) {
        if (datumVyroby == null) {
            throw new IllegalArgumentException("Datum vyroby nemuze byt NULL");
        }
        this.datumVyroby = datumVyroby;
    }

    public void setMajitel(Uzivatel majitel) {
        if (majitel == null) {
            throw new IllegalArgumentException("Majitel polozky nemuze byt null");
        }

        if (!DatabazeUzivatelu.getInstance().getUzivatel(majitel.getUserName()).equals(majitel)) {
            throw new IllegalArgumentException("Majitel musi byt v databazi a musi byt upToDate");
        }
        this.majitel = majitel;
    }

    public void setPopis(String popis) {
        if (popis == null) {
            throw new IllegalArgumentException("Popis polozky nemuze byt null");
        }
        for (String info : majitel.getPersonalInfo()) {
            if (popis.contains(info)) {
                throw new IllegalArgumentException("Popis nemuze obsahovat osobni udaje Majitele");
            }
        }
        this.popis = popis;
    }

    public Auto(String nazev, float cena, Pohon pohonVozidla, int pocetDveri, Date datumVyroby, Uzivatel majitel, String popis) {
        setNazev(nazev);
        setCena(cena);
        setPohonVozidla(pohonVozidla);
        setPocetDveri(pocetDveri);
        setDatumVyroby(datumVyroby);
        setMajitel(majitel);
        setPopis(popis);
    }

    @Override
    public int compareTo(Auto o) {
        return o.nazev.compareTo(this.nazev);
    }
}
