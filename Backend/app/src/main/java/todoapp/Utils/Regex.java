package todoapp.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static final String dateRegex = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})$" ;
    public static final String timeRegex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    public static final String orderTaskMenuRegex = "[0-6]";
    public static final String editTaskMenuRegex = "[0-6]";
    public static final String findTaskMenuRegex = "[0-6]";
    public static final String priority = "[1-5]";

    public static boolean isValidCommand(String command, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        boolean optionOk = matcher.matches();
        return optionOk;
    }

}
