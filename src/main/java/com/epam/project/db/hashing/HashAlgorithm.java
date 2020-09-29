package com.epam.project.db.hashing;

public enum HashAlgorithm {

    MD2("MD2"),
    MD5("MD5"),
    SHA_1("SHA-1"),
    /**!!!Best!!!
     *
     */
    SHA_256("SHA-256"),
    SHA_384("SHA-384"),
    SHA_512("SHA-512");

    private final String value;

    HashAlgorithm(String value)
    {
        this.value = value;
    }

    public final String value() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }
}
