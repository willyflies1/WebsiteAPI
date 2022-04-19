package com.api.website.service;

import java.util.HashMap;

public class Wallet {
    private static final String API_KEY = System.getenv("BINANCE_API_KEY");
    private static final String API_SECRET = System.getenv("BINANCE_API_SECRET");
    HashMap<String,String> parameters = new HashMap<String,String>();

    public Wallet() {
        String baseUrl = "https://api.binance.com";

    }
}
