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
        try {
            System.out.println(toHash("root",HashAlgorithm.SHA_256).concat(" root"));
            System.out.println(toHash("manager",HashAlgorithm.SHA_256).concat(" manager"));
            System.out.println(toHash("client",HashAlgorithm.SHA_256).concat(" client"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
