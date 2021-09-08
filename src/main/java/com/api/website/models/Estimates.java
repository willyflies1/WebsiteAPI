package com.api.website.models;

public class Estimates {

    private String time;
    private long blockReward;
    private long hashrate;
    private long blockTime;
    private long gasPrice;

    public Estimates() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getBlockReward() {
        return blockReward;
    }

    public void setBlockReward(long blockReward) {
        this.blockReward = blockReward;
    }

    public long getHashrate() {
        return hashrate;
    }

    public void setHashrate(long hashrate) {
        this.hashrate = hashrate;
    }

    public long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(long blockTime) {
        this.blockTime = blockTime;
    }

    public long getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(long gasPrice) {
        this.gasPrice = gasPrice;
    }
}
