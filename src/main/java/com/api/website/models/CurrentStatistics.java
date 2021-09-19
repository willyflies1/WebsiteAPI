package com.api.website.models;

public class CurrentStatistics extends MinerStatistics {
    private String lastSeen;

    public CurrentStatistics(){}

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
}
