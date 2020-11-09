package main.java.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Adresa extends Validator {
    public static final String pattern="^([0-9a-zA-Z- .,]{7,})$";
    public boolean isValid(String adresa){
        Pattern pattern = Pattern.compile(Adresa.pattern);
        Matcher matcher = pattern.matcher(adresa);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Musi byt slozena ze znaku 0-9,a-z,A-Z,'-',' ','.',',' a minimalne 7 znaku dlouha.");
        }
        return true;
    }
}
