package ru.itis.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunctions {
    private static MessageDigest md5;

    public static String getPasswordHashMD5(String password) {
        if (md5 == null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }

        return builder.toString();
    }
}
