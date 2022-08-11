package com.api.website.model;

public class TopMiner {
    private String miner;
    private long hashRate;

    public TopMiner() {

    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public long getHashRate() {
        return hashRate;
    }

    public void setHashRate(long hashRate) {
        this.hashRate = hashRate;
    }
}
