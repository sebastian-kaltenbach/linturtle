package de.dsheng.linturtle.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RuleCheckUtils {
    
    /*
     * String Tests 
     */
    public static boolean notNullOrEmpty(String stringToTest) {
        if(stringToTest != null) {
            return !stringToTest.isEmpty();
        }
        return false;
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

    public static boolean hasConnection(List<?> connections) {
        return connections.size() > 0;
    }

    public static boolean hasMultipleConnections(List<?> connections) {
        return connections.size() > 1;
    }

    public static boolean isIncoming(List<?> incoming, List<?> outgoing) {
        return incoming.size() > outgoing.size();
    }
}
