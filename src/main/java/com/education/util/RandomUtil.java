package com.education.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 6;

    private RandomUtil() {
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static Long generateId() {
        return Long.valueOf(RandomStringUtils.randomNumeric(DEF_COUNT));
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static String generateExamKey() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

}
