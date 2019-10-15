package org.ioak.mirror.service;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits + "!@#$%&*";

    private static final Random random = Objects.requireNonNull(new SecureRandom());;

    private static final char[] symbols = alphanum.toCharArray();


    /**
     * Generate a random string.
     */
    public static String nextString(int x) {
        char[] buf = new char[x];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
