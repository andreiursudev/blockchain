package org.example.cryptocurrency.blockchain;

import org.example.cryptocurrency.cryptocurrency.TransactionOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Blockchain {

	// this is the public ledger - everyone can access
	// all the blocks (previous transactions) on the blockchain
	public static List<Block> blockChain;
	// we store every unspent transactions
	public static Map<String, TransactionOutput> UTXOs;
	
	public Blockchain() {
		Blockchain.UTXOs = new HashMap<>();
		Blockchain.blockChain = new ArrayList<>();
	}
	
	public void addBlock(Block block) {
		Blockchain.blockChain.add(block);
	}
	
	public int size() {
		return Blockchain.blockChain.size();
	}
	
	@Override
	public String toString() {

		StringBuilder blockChain = new StringBuilder();
		
		for(Block block : Blockchain.blockChain)
			blockChain.append(block.toString()).append("\n");
		
		return blockChain.toString();
	}
}
