package com.api.website.models;

public class PoolStats {

    private long hashRate;
    private int miners;
    private int workers;
    private long blocksPerHour;

    public PoolStats() {

    }

    public long getHashRate() {
        return hashRate;
    }

    public void setHashRate(long hashRate) {
        this.hashRate = hashRate;
    }

    public int getMiners() {
        return miners;
    }

    public void setMiners(int miners) {
        this.miners = miners;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public long getBlocksPerHour() {
        return blocksPerHour;
    }

    public void setBlocksPerHour(long blocksPerHour) {
        this.blocksPerHour = blocksPerHour;
    }
}
