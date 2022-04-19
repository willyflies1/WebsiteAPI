package com.api.website.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

public class SnapshotData implements Serializable {
    @JsonProperty("balanaces")
    private SnapshotBalance[] balances;

    @JsonProperty("totalAssetOfBtc")
    private String totalAssetOfBtc;

    public SnapshotData() {
    }

    public SnapshotData(SnapshotBalance[] balances, String totalAssetOfBtc) {
        this.balances = balances;
        this.totalAssetOfBtc = totalAssetOfBtc;
    }

    @Override
    public String toString() {
        return "SnapshotData{" +
                "balances=" + Arrays.toString(balances) +
                ", totalAssetOfBtc='" + totalAssetOfBtc + '\'' +
                '}';
    }

    public SnapshotBalance[] getBalances() {
        return balances;
    }

    public void setBalances(SnapshotBalance[] balances) {
        this.balances = balances;
    }

    public String getTotalAssetOfBtc() {
        return totalAssetOfBtc;
    }

    public void setTotalAssetOfBtc(String totalAssetOfBtc) {
        this.totalAssetOfBtc = totalAssetOfBtc;
    }
}
