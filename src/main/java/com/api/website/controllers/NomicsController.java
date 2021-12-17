package com.api.website.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NomicsController {

    private String apiKey = "bb4f3224ff22ba0278691bc87525c6df131a7bc4";

    @GetMapping("/nomics")
    public void getCurrencies(){}
}
