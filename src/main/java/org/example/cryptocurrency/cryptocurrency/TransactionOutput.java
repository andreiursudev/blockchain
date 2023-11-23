package org.example.cryptocurrency.cryptocurrency;

import java.security.PublicKey;

public class TransactionOutput {

	// identifier of the transaction output (SHA-256)
	private String id;
	// transaction id of the parent (so the transaction it was created in)
	private final String parentTransactionId;
	// the new owner of the coin 
	private final PublicKey receiver;
	// amount of coins
	private final double amount;
	
	public TransactionOutput(PublicKey receiver, double amount, String parentTransactionId) {
		this.receiver = receiver;
		this.amount = amount;
		this.parentTransactionId = parentTransactionId;
		generateId();
	}
	
	private void generateId() {
		this.id = CryptographyHelper.generateHash(receiver.toString()+ amount +
				parentTransactionId);
	}
	
	public boolean isMine(PublicKey publicKey) {
		return publicKey == receiver;
	}
	
	public String getId() {
		return id;
	}


	public double getAmount() {
		return amount;
	}

}
