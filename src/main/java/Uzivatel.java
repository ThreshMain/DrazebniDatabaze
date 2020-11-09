
import Validator.*;

import javax.management.InstanceAlreadyExistsException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.Objects;

public class Uzivatel {
    private String jmeno;

    public String getJmeno() {
        return jmeno;
    }

    public String getUserName() {
        return userName;
    }

    public Password getHeslo() {
        return heslo;
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

    public LinkedList<String> getPersonalInfo(){
        LinkedList<String> allInfo=new LinkedList<>();
        allInfo.add(telefon);
        allInfo.add(mail);
        allInfo.add(adresa);
        return allInfo;
    }

    private String userName;
    private Password heslo;
    private String mail;
    private String telefon;
    private String adresa;

    public void setJmeno(String jmeno){
        if (jmeno == null) {
            throw new IllegalArgumentException("Jmeno nemuze byt null");
        }
        Validator validator = new Jmeno();
        validator.isValid(jmeno);
        this.jmeno = jmeno;
        if (jmeno.length() < 3) {
            throw new IllegalArgumentException("Jmeno musi byt delsi nez 2 znaky");
        }
    }

    public void setUserName(String userName) throws InstanceAlreadyExistsException {
        if (DatabazeUzivatelu.getInstance().Contains(userName)) {
            throw new InstanceAlreadyExistsException("Uzivatel s timto jmenem je jiz v databazi");
        }
        Validator validator = new UserName();
        validator.isValid(userName);
        this.userName = userName;
    }

    public void setHeslo(String heslo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (jmeno == null) {
            throw new IllegalArgumentException("Jmeno nemuze byt null");
        }
        Validator password = new PasswordValidator();
        password.isValid(heslo);
        this.heslo = new Password(heslo);
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

    public Uzivatel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzivatel uzivatel = (Uzivatel) o;
        return jmeno.equals(uzivatel.jmeno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmeno);
    }

    @Override
    public String toString() {
        return "Uzivatel{" +
                "userName='" + userName + '\'' +
                '}';
    }
}