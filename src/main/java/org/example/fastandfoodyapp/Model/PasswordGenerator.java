package org.example.fastandfoodyapp.Model;

import java.security.SecureRandom;

public class PasswordGenerator {
    private final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private final String NUMBER = "0123456789";

    private final String PASSWORD_ALLOW =
            CHAR_LOWER + CHAR_UPPER + NUMBER;
    private final int PASSWORD_LENGTH = 8;

    private SecureRandom random = new SecureRandom();

    public String generateRandomPassword(int length) {
        if (length < 1) throw new IllegalArgumentException("Length must be at least 1 character");
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW.length());
            password.append(PASSWORD_ALLOW.charAt(randomIndex));
        }

        return password.toString();
    }
}
