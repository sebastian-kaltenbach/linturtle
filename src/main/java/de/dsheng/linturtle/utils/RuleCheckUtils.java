package de.dsheng.linturtle.utils;

public final class RuleCheckUtils {
    
    public static boolean nonNullOrEmpty(String stringToTest) {
        return (stringToTest == null || stringToTest.isEmpty()) ? false : true;
    }
}
