package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserName extends Validator{

    public static final String pattern="^.{4,24}$";
    @Override
    public boolean isValid(String username) {
        Pattern pattern = Pattern.compile(UserName.pattern);
        Matcher matcher = pattern.matcher(username);
        if(!matcher.matches()){
            throw new IllegalArgumentException("UserName musi byt od 4 do 24 ti znaku");
        }
        return true;
    }
}
