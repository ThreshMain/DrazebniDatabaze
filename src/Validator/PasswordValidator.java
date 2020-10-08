package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends Validator {
    public static final String pattern="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    @Override
    public boolean isValid(String password) {
        Pattern pattern = Pattern.compile(PasswordValidator.pattern);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Heslo musi byt vic jak 7 znaku a musi obsahavat 1 velke a male pismeno a jednu cislici");
        }
        return true;
    }
}
