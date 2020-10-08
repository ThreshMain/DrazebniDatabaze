package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail extends Validator {
    public static final String pattern="(.+)@(.+)(\\.{1})(.+)";
    public boolean isValid(String mail){
        Pattern pattern = Pattern.compile(Mail.pattern);
        Matcher matcher = pattern.matcher(mail);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Email neodpovida formatu something@something");
        }
        return true;
    }
}
