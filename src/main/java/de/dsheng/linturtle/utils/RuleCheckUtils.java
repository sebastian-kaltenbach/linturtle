package de.dsheng.linturtle.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RuleCheckUtils {
    
    /*
     * String Tests 
     */
    public static boolean nonNullOrEmpty(String stringToTest) {
        return (stringToTest == null || stringToTest.isEmpty()) ? false : true;
    }

    public static boolean endsWith(String stringToTest, String character) {
        return stringToTest.endsWith(character);
    }

    public static boolean startsWith(String stringToTest, String character) {
        return stringToTest.startsWith(character);
    }

    public static boolean contains(String stringToTest, String character) {
        return stringToTest.contains(character);
    }

    public static boolean isPattern(String stringToTest, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(stringToTest);
        return matcher.matches();
    }

    public static boolean hasConnection(List<?> incoming) {
        return incoming.size() > 0;
    }
}
