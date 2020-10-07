package com.epam.project.db.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String toHash(String input, HashAlgorithm algorithm) throws NoSuchAlgorithmException {

        StringBuilder strBld = new StringBuilder();

        MessageDigest digest = MessageDigest.getInstance(algorithm.toString());

        digest.update(input.getBytes());

        byte[] hash = digest.digest();

        for (byte b : hash) {
            strBld.append(String.format("%02X", b));
        }

        return strBld.toString();
    }

    public static void main(String[] args) {
        String str = "root";
        try {
            str = toHash(str,HashAlgorithm.SHA_256);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
