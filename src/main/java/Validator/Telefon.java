package main.java.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefon extends Validator {
    public static final String pattern="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{3}$";
    public boolean isValid(String telefon){
        Pattern pattern = Pattern.compile(Telefon.pattern);
        Matcher matcher = pattern.matcher(telefon);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Telefoni cislo neodpovida formatu +XXX XXX XXX XXX");
        }
        return true;
    }
}
