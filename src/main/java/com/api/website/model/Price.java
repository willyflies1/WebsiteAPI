package com.api.website.model;

public class Price {
    private String time;
    private long usd;
    private long btc;
    private long eur;
    private long cny;
    private long rub;

    public Price() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getUsd() {
        return usd;
    }

    public void setUsd(long usd) {
        this.usd = usd;
    }

    public long getBtc() {
        return btc;
    }

    public void setBtc(long btc) {
        this.btc = btc;
    }

    public long getEur() {
        return eur;
    }

    public void setEur(long eur) {
        this.eur = eur;
    }

    public long getCny() {
        return cny;
    }

    public void setCny(long cny) {
        this.cny = cny;
    }

    public long getRub() {
        return rub;
    }

    public void setRub(long rub) {
        this.rub = rub;
    }
}
