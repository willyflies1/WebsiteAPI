package com.api.website.model;

public class Settings {
    private String email;   // Masked email address of the miner
    private int monitor;    // 1 for yes, 0 for no
    private long minPayout; // minimum payout amount defined by the miner in base units
    private String ip;      // masked ip address of the highest performing worker

    public Settings(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public long getMinPayout() {
        return minPayout;
    }

    public void setMinPayout(long minPayout) {
        this.minPayout = minPayout;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
