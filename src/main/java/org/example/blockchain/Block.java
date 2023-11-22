package org.example.blockchain;

import org.example.hashing.SHA256Helper;

import java.util.Date;

public class Block {

    private final int id;
    private int nonce;
    private final long timeStamp;
    private String hash;
    private final String previousHash;
    private final String transaction;

    public Block(int id, String transaction, String previousHash) {
        this.id = id;
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        generateHash();
    }

    public void generateHash() {
        String dataToHash = id +
                previousHash +
                timeStamp +
                nonce +
                transaction;
        this.hash = SHA256Helper.hash(dataToHash);
    }

    public void incrementNonce() {
        this.nonce++;
    }

    public String getHash() {
        return this.hash;
    }

    @Override
    public String toString() {
        return "Block [id=" + id + ", hash=" + hash + ", previousHash=" +
                previousHash + ", transaction=" + transaction + "]";
    }
}
