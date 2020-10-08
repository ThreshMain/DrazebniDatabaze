package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Adresa extends Validator {
    //TODO:Regex fix
    public static final String pattern="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{3}$";
    public boolean isValid(String adresa){
        Pattern pattern = Pattern.compile(Adresa.pattern);
        Matcher matcher = pattern.matcher(adresa);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Adresa neni ve formatu");
        }
        return true;
    }
}
