package main.java.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jmeno extends Validator {
    public static final String pattern="^.{4,12}$";
    @Override
    public boolean isValid(String jmeno) {
        Pattern pattern = Pattern.compile(Jmeno.pattern);
        Matcher matcher = pattern.matcher(jmeno);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Jmeno musi byt od 4 do 12 ti znaku");
        }
        return true;
    }
}
