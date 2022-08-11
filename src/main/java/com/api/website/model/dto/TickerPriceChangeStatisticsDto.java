package com.api.website.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TickerPriceChangeStatisticsDto implements Serializable {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("priceChange")
    private String priceChange;
    @JsonProperty("priceChangePercent")
    private String priceChangePercent;
    @JsonProperty("weightedAvgPrice")
    private String weightedAvgPrice;
    @JsonProperty("prevClosePrice")
    private String prevClosePrice;
    @JsonProperty("lastPrice")
    private String lastPrice;
    @JsonProperty("lastQty")
    private String lastQty;
    @JsonProperty("bidPrice")
    private String bidPrice;
    @JsonProperty("bidQty")
    private String bidQty;
    @JsonProperty("askPrice")
    private String askPrice;
    @JsonProperty("askQty")
    private String askQty;
    @JsonProperty("openPrice")
    private String openPrice;
    @JsonProperty("highPrice")
    private String highPrice;
    @JsonProperty("lowPrice")
    private String lowPrice;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("quoteVolume")
    private String quoteVolume;
    @JsonProperty("openTime")
    private double openTime;
    @JsonProperty("closeTime")
    private double closeTime;
    @JsonProperty("firstId")
    private double firstId;
    @JsonProperty("lastId")
    private double lastId;
    @JsonProperty("count")
    private double count;

    // ** Constructors **
    // Without an empty constructor, com.fasterxml.jackson.databind.exc will be thrown
    public TickerPriceChangeStatisticsDto(){}

    public TickerPriceChangeStatisticsDto(String symbol, String priceChange, String priceChangePercent,
                                          String weightedAvgPrice, String prevClosePrice, String lastPrice,
                                          String lastQty, String bidPrice, String bidQty, String askPrice,
                                          String askQty, String openPrice, String highPrice, String lowPrice,
                                          String volume, String quoteVolume, double openTime, double closeTime,
                                          double firstId, double lastId, double count) {
        this.symbol = symbol;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.weightedAvgPrice = weightedAvgPrice;
        this.prevClosePrice = prevClosePrice;
        this.lastPrice = lastPrice;
        this.lastQty = lastQty;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.firstId = firstId;
        this.lastId = lastId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "TickerPriceChangeStatisticsDto{" +
                "symbol='" + symbol + '\'' +
                ", priceChange='" + priceChange + '\'' +
                ", priceChangePercent='" + priceChangePercent + '\'' +
                ", weightedAvgPrice='" + weightedAvgPrice + '\'' +
                ", prevClosePrice='" + prevClosePrice + '\'' +
                ", lastPrice='" + lastPrice + '\'' +
                ", lastQty='" + lastQty + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                ", bidQty='" + bidQty + '\'' +
                ", askPrice='" + askPrice + '\'' +
                ", askQty='" + askQty + '\'' +
                ", openPrice='" + openPrice + '\'' +
                ", highPrice='" + highPrice + '\'' +
                ", lowPrice='" + lowPrice + '\'' +
                ", volume='" + volume + '\'' +
                ", quoteVolume='" + quoteVolume + '\'' +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", firstId=" + firstId +
                ", lastId=" + lastId +
                ", count=" + count +
                '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(String priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getWeightedAvgPrice() {
        return weightedAvgPrice;
    }

    public void setWeightedAvgPrice(String weightedAvgPrice) {
        this.weightedAvgPrice = weightedAvgPrice;
    }

    public String getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(String prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getLastQty() {
        return lastQty;
    }

    public void setLastQty(String lastQty) {
        this.lastQty = lastQty;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidQty() {
        return bidQty;
    }

    public void setBidQty(String bidQty) {
        this.bidQty = bidQty;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getAskQty() {
        return askQty;
    }

    public void setAskQty(String askQty) {
        this.askQty = askQty;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public double getOpenTime() {
        return openTime;
    }

    public void setOpenTime(double openTime) {
        this.openTime = openTime;
    }

    public double getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(double closeTime) {
        this.closeTime = closeTime;
    }

    public double getFirstId() {
        return firstId;
    }

    public void setFirstId(double firstId) {
        this.firstId = firstId;
    }

    public double getLastId() {
        return lastId;
    }

    public void setLastId(double lastId) {
        this.lastId = lastId;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
