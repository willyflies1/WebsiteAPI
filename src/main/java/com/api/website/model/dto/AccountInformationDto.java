package com.api.website.model.dto;

import com.api.website.model.AccountBalance;
import com.api.website.model.AccountPermission;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountInformationDto implements Serializable {
    @JsonProperty("makerCommission")
    Integer makerCommission;

    @JsonProperty("takerCommission")
    Integer takerCommision;

    @JsonProperty("buyerCommission")
    Integer buyerCommision;

    @JsonProperty("sellerCommission")
    Integer sellerCommsion;

    @JsonProperty("canTrade")
    Boolean canTrade;

    @JsonProperty("canWithdraw")
    Boolean canWithdraw;

    @JsonProperty("canDeposit")
    Boolean canDeposit;

    @JsonProperty("updateTime")
    Long updateTime;

    @JsonProperty("accountType")
    String accountType;

    @JsonProperty("balances")
    ArrayList<AccountBalance> balances;

    @JsonProperty("permissions")
    ArrayList<AccountPermission> permissions;

    public AccountInformationDto() {
    }

    public AccountInformationDto(Integer makerCommission, Integer takerCommision, Integer buyerCommision,
                                 Integer sellerCommsion, Boolean canTrade, Boolean canWithdraw,
                                 Boolean canDeposit, Long updateTime, String accountType,
                                 ArrayList<AccountBalance> balances, ArrayList<AccountPermission> permissions) {
        this.makerCommission = makerCommission;
        this.takerCommision = takerCommision;
        this.buyerCommision = buyerCommision;
        this.sellerCommsion = sellerCommsion;
        this.canTrade = canTrade;
        this.canWithdraw = canWithdraw;
        this.canDeposit = canDeposit;
        this.updateTime = updateTime;
        this.accountType = accountType;
        this.balances = balances;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "AccountInformtaionDto{" +
                "makerCommission=" + makerCommission +
                ", takerCommision=" + takerCommision +
                ", buyerCommision=" + buyerCommision +
                ", sellerCommsion=" + sellerCommsion +
                ", canTrade=" + canTrade +
                ", canWithdraw=" + canWithdraw +
                ", canDeposit=" + canDeposit +
                ", updateTime=" + updateTime +
                ", accountType='" + accountType + '\'' +
                ", balances=" + balances +
                ", permissions=" + permissions +
                '}';
    }

    public Integer getMakerCommission() {
        return makerCommission;
    }

    public void setMakerCommission(Integer makerCommission) {
        this.makerCommission = makerCommission;
    }

    public Integer getTakerCommision() {
        return takerCommision;
    }

    public void setTakerCommision(Integer takerCommision) {
        this.takerCommision = takerCommision;
    }

    public Integer getBuyerCommision() {
        return buyerCommision;
    }

    public void setBuyerCommision(Integer buyerCommision) {
        this.buyerCommision = buyerCommision;
    }

    public Integer getSellerCommsion() {
        return sellerCommsion;
    }

    public void setSellerCommsion(Integer sellerCommsion) {
        this.sellerCommsion = sellerCommsion;
    }

    public Boolean getCanTrade() {
        return canTrade;
    }

    public void setCanTrade(Boolean canTrade) {
        this.canTrade = canTrade;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<AccountBalance> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<AccountBalance> balances) {
        this.balances = balances;
    }

    public ArrayList<AccountPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<AccountPermission> permissions) {
        this.permissions = permissions;
    }
}
