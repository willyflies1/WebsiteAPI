package com.api.website.model;

public class Workers {
    private String worker;
    private String time;
    private String lastSeen;
    private long reportedHashrate;
    private long averageHashrate;
    private long currentHashrate;
    private long validShares;
    private long invalidShares;
    private long staleShares;

    public Workers(){}

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public long getReportedHashrate() {
        return reportedHashrate;
    }

    public void setReportedHashrate(long reportedHashrate) {
        this.reportedHashrate = reportedHashrate;
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
}
