package org.example.blockchain;

import java.util.LinkedList;
import java.util.List;

public class BlockChain {

    // immutable ledger
    // we are not able to remove blocks
    private List<Block> blockChain;

    public BlockChain() {
        this.blockChain = new LinkedList<>();
    }

    public void addBlock(Block block) {
        this.blockChain.add(block);
    }

    public List<Block> getBlockChain() {
        return this.blockChain;
    }

    public int getSize() {
        return this.blockChain.size();
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        for (Block block : this.blockChain)
            s.append(block.toString()).append("\n");

        return s.toString();
    }

    public String getHashOfLastBlock() {
        return blockChain.get(blockChain.size() - 1).getHash();
    }
}
