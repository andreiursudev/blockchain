package org.example.cryptocurrency.cryptocurrency;

import org.example.cryptocurrency.blockchain.Blockchain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class Transaction {

	// id of the transaction is a hash
	private String transactionId;
	// we use PublicKeys to reference the sender or receiver
	private final PublicKey sender;
	private final PublicKey receiver;
	//amount of coins the transaction sends to the receiver from the sender
	private final double amount;
	// make sure the transaction is signed to prevent anyone else from spending the coins
	private byte[] signature;
	
	// every transaction has inputs and outputs
	public final List<TransactionInput> inputs;
	public final List<TransactionOutput> outputs;
	
	public Transaction(PublicKey sender, PublicKey receiver, double amount,  
			List<TransactionInput> inputs) {
		this.outputs = new ArrayList<>();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.inputs = inputs;
		calculateHash();
	}
	
	public boolean verifyTransaction() {
		
		if(!verifySignature()) {
			System.out.println("Invalid transaction because of invalid signature...");
			return false;
		}
		
		// let's gather unspent transactions (we have to consider the inputs)
		for(TransactionInput transactionInput : inputs)
			transactionInput.setUTXO(Blockchain.UTXOs.get(transactionInput.getTransactionOutputId()));
		
		//transactions have 2 part: send an amount to the receiver + send the (balance-amount)
		// back to the sender
		//send value to recipient
		outputs.add(new TransactionOutput( this.receiver, amount, transactionId));
		//send the left over 'change' back to sender		
		outputs.add(new TransactionOutput( this.sender, getInputsSum() - amount, transactionId)); 
			
		// WE HAVE TO UPDATE THE UTXOs
		//the outputs will be inputs for other transactions (so put them in the blockchain's UTXOs)
		for(TransactionOutput transactionOutput : outputs)
			Blockchain.UTXOs.put(transactionOutput.getId() , transactionOutput);
			
		// remove transaction inputs from blockchain's UTXOs list because they've been spent
		for(TransactionInput transactionInput : inputs) 
			if(transactionInput.getUTXO() != null)   
				Blockchain.UTXOs.remove(transactionInput.getUTXO().getId());
		
		return true;
	}
	
	// this is how we calculate that how much money the sender has
	// we have to consider transactions in the past
	private double getInputsSum() {
		double sum = 0;
		
		for(TransactionInput transactionInput : inputs)
			if(transactionInput.getUTXO() != null) 
				sum += transactionInput.getUTXO().getAmount();
		
		return sum;
	}
	
	public void generateSignature(PrivateKey privateKey) {
		String data = sender.toString() + receiver.toString() + amount;
		signature = CryptographyHelper.sign(privateKey, data);
	}
	
	public boolean verifySignature() {
		String data = sender.toString() + receiver.toString() + amount;
		return CryptographyHelper.verify(sender, data, signature);
	}

	private void calculateHash() {
		String hashData = sender.toString()+receiver.toString()+ amount;
		this.transactionId = CryptographyHelper.generateHash(hashData);
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public PublicKey getReceiver() {
		return receiver;
	}

	public double getAmount() {
		return amount;
	}

}
