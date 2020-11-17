import Validator.PasswordValidator;
import Validator.UserName;
import Validator.Validator;

import javax.management.InstanceAlreadyExistsException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Uzivatel extends Osoba {
    private Password heslo;
    private String userName;

    public Password getHeslo() {
        return heslo;
    }

    public String getUserName() {
        return userName;

    }

    public Uzivatel(String userName, String mail, String heslo) throws InvalidKeySpecException, NoSuchAlgorithmException, InstanceAlreadyExistsException {
        super();
        setHeslo(heslo);
        setUserName(userName);
        setMail(mail);
    }


    public void setUserName(String userName) throws InstanceAlreadyExistsException {
        if (DatabazeUzivatelu.getInstance().contains(userName)) {
            throw new InstanceAlreadyExistsException("Uzivatel s timto jmenem je jiz v databazi");
        }
        Validator validator = new UserName();
        validator.isValid(userName);
        this.userName = userName;

    }

    public void setHeslo(String heslo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (heslo == null) {
            throw new IllegalArgumentException("Jmeno nemuze byt null");
        }
        Validator password = new PasswordValidator();
        password.isValid(heslo);
        this.heslo = new Password(heslo);
    }

}