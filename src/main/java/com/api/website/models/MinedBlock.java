package com.api.website.models;

public class MinedBlock {

    private int number;
    private String miner;
    private String time;

    public MinedBlock() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
