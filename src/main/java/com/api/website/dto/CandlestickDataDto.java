package com.api.website.dto;

public class CandlestickDataDto {
    private final double ignore;  // ????
    private double openTime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double closeTime;
    private double quoteAssetVolume;
    private int numberOfTrades;
    private double takerBuyAssetVolume;
    private double takerBuyQuoteVolume;

    // ** Constructor
    public CandlestickDataDto(double openTime, double open, double high, double low, double close, double volume,
                              double closeTime, double quoteAssetVolume, int numberOfTrades, double takerBuyAssetVolume,
                              double takerBuyQuoteVolume, double ignore) {
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyAssetVolume = takerBuyAssetVolume;
        this.takerBuyQuoteVolume = takerBuyQuoteVolume;
        this.ignore = ignore;
    }

    // ** Getters & Setters
    public double getOpenTime() {
        return openTime;
    }

    public void setOpenTime(double openTime) {
        this.openTime = openTime;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(double closeTime) {
        this.closeTime = closeTime;
    }

    public double getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(double quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public double getTakerBuyAssetVolume() {
        return takerBuyAssetVolume;
    }

    public void setTakerBuyAssetVolume(double takerBuyAssetVolume) {
        this.takerBuyAssetVolume = takerBuyAssetVolume;
    }

    public double getTakerBuyQuoteVolume() {
        return takerBuyQuoteVolume;
    }

    public void setTakerBuyQuoteVolume(double takerBuyQuoteVolume) {
        this.takerBuyQuoteVolume = takerBuyQuoteVolume;
    }
}
