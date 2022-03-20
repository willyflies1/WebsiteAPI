package com.api.website.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DepositHistoryDto implements Serializable {
    @JsonProperty("amount")
    String amount;

    @JsonProperty("coin")
    String coin;

    @JsonProperty("network")
    String network;

    @JsonProperty("status")
    Integer status;

    @JsonProperty("address")
    String address;

    @JsonProperty("addressTag")
    String addressTag;

    @JsonProperty("txId")
    String txId;

    @JsonProperty("insertTime")
    Long insertTime;

    @JsonProperty("transferType")
    Integer transferType;

    @JsonProperty("unlockConfirm")
    String unlockConfirm;   // confirm times for unlocking

    @JsonProperty("confirmTimes")
    String confirmTimes;

    public DepositHistoryDto() {
    }

    public DepositHistoryDto(String amount, String coin, String network, Integer status, String address,
                             String addressTag, String txId, Long insertTime, Integer transferType,
                             String unlockConfirm, String confirmTimes) {
        this.amount = amount;
        this.coin = coin;
        this.network = network;
        this.status = status;
        this.address = address;
        this.addressTag = addressTag;
        this.txId = txId;
        this.insertTime = insertTime;
        this.transferType = transferType;
        this.unlockConfirm = unlockConfirm;
        this.confirmTimes = confirmTimes;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressTag() {
        return addressTag;
    }

    public void setAddressTag(String addressTag) {
        this.addressTag = addressTag;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public String getUnlockConfirm() {
        return unlockConfirm;
    }

    public void setUnlockConfirm(String unlockConfirm) {
        this.unlockConfirm = unlockConfirm;
    }

    public String getConfirmTimes() {
        return confirmTimes;
    }

    public void setConfirmTimes(String confirmTimes) {
        this.confirmTimes = confirmTimes;
    }
}
