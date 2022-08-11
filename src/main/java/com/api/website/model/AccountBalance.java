package com.api.website.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AccountBalance implements Serializable {

    @JsonProperty("asset")
    String asset;

    @JsonProperty("free")
    Double free;

    @JsonProperty("locked")
    Double locked;

    public AccountBalance() {
    }

    public AccountBalance(String asset, Double free, Double locked) {
        this.asset = asset;
        this.free = free;
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
                "asset='" + asset + '\'' +
                ", free=" + free +
                ", locked=" + locked +
                '}';
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
    }

    public Double getLocked() {
        return locked;
    }

    public void setLocked(Double locked) {
        this.locked = locked;
    }
}
