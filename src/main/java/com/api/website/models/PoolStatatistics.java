package com.api.website.models;


public class PoolStatatistics {
    private TopMiner topMiners[];

    private MinedBlock minedBlocks[];

    private PoolStats poolStats;

    private Price price;

    private Estimates estimates;

    public PoolStatatistics() {

    }

    public TopMiner[] getTopMiners() {
        return topMiners;
    }

    public void setTopMiners(TopMiner[] topMiners) {
        this.topMiners = topMiners;
    }

    public MinedBlock[] getMinedBlocks() {
        return minedBlocks;
    }

    public void setMinedBlocks(MinedBlock[] minedBlocks) {
        this.minedBlocks = minedBlocks;
    }

    public PoolStats getPoolStats() {
        return poolStats;
    }

    public void setPoolStats(PoolStats poolStats) {
        this.poolStats = poolStats;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Estimates getEstimates() {
        return estimates;
    }

    public void setEstimates(Estimates estimates) {
        this.estimates = estimates;
    }
}
