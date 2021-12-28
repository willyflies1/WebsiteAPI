package com.api.website.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnapshotBalance {
    @JsonProperty("asset")
    private String asset;

    @JsonProperty("free")
    private String free;

    @JsonProperty("locked")
    private String locked;

    public SnapshotBalance() {
    }

    public SnapshotBalance(String asset, String free, String locked) {
        this.asset = asset;
        this.free = free;
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "SnapshotBalance{" +
                "asset='" + asset + '\'' +
                ", free='" + free + '\'' +
                ", locked='" + locked + '\'' +
                '}';
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
