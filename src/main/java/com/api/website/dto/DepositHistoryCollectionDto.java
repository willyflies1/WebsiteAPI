package com.api.website.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class DepositHistoryCollectionDto implements Serializable {
    @JsonProperty("deposits")
    ArrayList<DepositHistoryDto> deposits;

    public DepositHistoryCollectionDto() {
    }

    public DepositHistoryCollectionDto(ArrayList<DepositHistoryDto> deposits) {
        this.deposits = deposits;
    }

    public ArrayList<DepositHistoryDto> getDeposits() {
        return deposits;
    }

    public void setDeposits(ArrayList<DepositHistoryDto> deposits) {
        this.deposits = deposits;
    }

    @Override
    public String toString() {
        return "DepositHistoryCollectionDto{" +
                "deposit=" + deposits +
                '}';
    }
}
