package com.api.website.model;

public class CurrentStatistics extends MinerStatistics {
    private String lastSeen;
    private double unpaid;
    private double unconfirmed;
    private boolean formated = false;

    public CurrentStatistics() {
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public double getUnpaid() {
        return unpaid / Math.pow(10, 17);
    }

    public void setUnpaid(long unpaid) {
        this.unpaid = unpaid;
    }

    public double getUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(long unconfirmed) {
        this.unconfirmed = unconfirmed;
    }
}
