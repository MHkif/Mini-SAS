package app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    private static String regexString = "^(?![0-9\\s]+$)[A-Za-z][A-Za-z\\s]*$\n";
    private static String regexInteger = "^[0-9]+$";

    public static boolean validString(String input){

        String pattern = "^[A-Za-z][A-Za-z\\s]*$";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(input);

        // Check if the input matches the pattern
        return !input.isEmpty() && !input.matches(".*\\d.*") && matcher.matches();



    }


    public static boolean validInteger(String str){

        if(Pattern.matches(regexInteger, str)){
            return true;
        }else {
            return false;
        }
    }


}
