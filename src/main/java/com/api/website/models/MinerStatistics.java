package com.api.website.models;

public class MinerStatistics {
    private String time;
    private long reportedHashrate;
    private long averageHashrate;
    private long currentHashrate;
    private long validShares;
    private long invalidShares;
    private long staleShares;
    private long activeWorkers;
    // Variables needed for logic to format currency
    private MinerCurrency selectedCurrency = MinerCurrency.ETH;
    private boolean formated = false;

    public MinerStatistics(){}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getReportedHashrate() {
        return reportedHashrate;
    }

    public void setReportedHashrate(long hashrate) {
        this.reportedHashrate = hashrate;
    }

    public long getAverageHashrate() {
        return averageHashrate;
    }

    public void setAverageHashrate(long averageHashrate) {
        this.averageHashrate = averageHashrate;
    }

    public long getCurrentHashrate() {
        return currentHashrate;
    }

    public void setCurrentHashrate(long currentHashrate) {
        this.currentHashrate = currentHashrate;
    }

    public long getValidShares() {
        return validShares;
    }

    public void setValidShares(long validShares) {
        this.validShares = validShares;
    }

    public long getInvalidShares() {
        return invalidShares;
    }

    public void setInvalidShares(long invalidShares) {
        this.invalidShares = invalidShares;
    }

    public long getStaleShares() {
        return staleShares;
    }

    public void setStaleShares(long staleShares) {
        this.staleShares = staleShares;
    }

    public long getActiveWorkers() {
        return activeWorkers;
    }

    public void setActiveWorkers(long activeWorkers) {
        this.activeWorkers = activeWorkers;
    }
}
