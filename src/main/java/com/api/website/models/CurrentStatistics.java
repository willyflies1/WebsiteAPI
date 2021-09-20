package com.api.website.models;

public class CurrentStatistics extends MinerStatistics {
    private String lastSeen;
    private long unpaid;
    private long unconfirmed;

    public CurrentStatistics(){}

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public long getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(long unpaid) {
        this.unpaid = unpaid;
    }

    public long getUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(long unconfirmed) {
        this.unconfirmed = unconfirmed;
    }
}
