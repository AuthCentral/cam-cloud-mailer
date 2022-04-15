package tech.mayanksoni.cam.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GeneratorUtils {
    private static final String GENERATOR_ALGORITHM = "SHA1PRNG";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERIC = "0123456789";
    private static final String ALPHANUMERIC = ALPHA + NUMERIC;
    private static SecureRandom secureRandom;

    static {
        try {
            secureRandom = SecureRandom.getInstance(GENERATOR_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String buildRandomString(String charset, int length) {
        StringBuilder builder = new StringBuilder();
        for (int iDx = 0; iDx < length; iDx++) {
            builder.append(charset.charAt(secureRandom.nextInt(charset.length())));
        }
        return builder.toString();

    }
    public static String buildRandomNumberSequence(int length){
        return buildRandomString(NUMERIC, length);
    }
    public static String buildRandomAlphanumericSequence(int length){
        return buildRandomString(ALPHANUMERIC, length);
    }

}
