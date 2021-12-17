package com.api.website.models;

public class CandlestickData {
    private Double openTime;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private int numberOfTrades;
    private Double closeTime;
    private Double volume;

    public CandlestickData(Double openTime,
                           Double openPrice,
                           Double highPrice,
                           Double lowPrice,
                           Double closePrice,
                           int numberOfTrades,
                           Double closeTime,
                           Double volume) {
        this.openTime = openTime;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.numberOfTrades = numberOfTrades;
        this.closeTime = closeTime;
        this.volume = volume;
    }

    public Double getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Double openTime) {
        this.openTime = openTime;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public Double getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Double closeTime) {
        this.closeTime = closeTime;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
