package framework.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringRandomizer {
    public static String makeRandomString(int length) {
        return RandomStringUtils.random(length, true, false);
    }
}
