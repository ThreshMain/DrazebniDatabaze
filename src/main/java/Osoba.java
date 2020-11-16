import Validator.Jmeno;
import Validator.Mail;
import Validator.Telefon;
import Validator.Validator;

import java.util.LinkedList;
import java.util.Objects;

public class Osoba {
    private String telefon;
    private String mail;
    private String jmeno;
    private String adresa;

    public String getJmeno() {
        return jmeno;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public LinkedList<String> getPersonalInfo() {
        LinkedList<String> allInfo = new LinkedList<>();
        allInfo.add(telefon);
        allInfo.add(mail);
        allInfo.add(adresa);
        return allInfo;
    }

    public void setJmeno(String jmeno) {
        if (jmeno == null) {
            throw new IllegalArgumentException("Jmeno nemuze byt null");
        }
        Validator validator = new Jmeno();
        validator.isValid(jmeno);
        this.jmeno = jmeno;
    }

    public void setMail(String mail) {
        if (mail == null) {
            throw new IllegalArgumentException("Mail nemuze byt null");
        }
        Validator validator = new Mail();
        validator.isValid(mail);
        this.mail = mail;
    }

    public void setTelefon(String telefon) {
        if (telefon == null) {
            throw new IllegalArgumentException("Validator.Telefon nemuze byt null");
        }
        Validator validator = new Telefon();
        validator.isValid(telefon);
        this.telefon = telefon;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return jmeno.equals(osoba.jmeno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmeno);
    }
}
