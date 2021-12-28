package com.api.website.service;

import com.api.website.controller.LoggingController;
import com.api.website.dto.WalletAccountSnapshotDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private String baseUrl;
    private String apiKey;
    private String apiSecret;
    private HashMap<String, String> parameters;
    private Signature sign = new Signature();
    private HttpHeaders headers = new HttpHeaders();
//    private final WebClient

    private Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public Request(String baseUrl, String apiKey, String apiSecret) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    /**
     * Concatenate query parameters
     * @param parameters HashMap of parameters
     * @return string of query parameters
     */
    private String joinQueryParameters(HashMap<String, String> parameters) {
        String urlPath = "";
        boolean isFirst = true;

        for (Map.Entry mapElement : parameters.entrySet()) {
            if (isFirst) {
                isFirst = false;
                urlPath += (String) mapElement.getKey() + "=" + (String) mapElement.getValue();
            } else {
                urlPath += "&" + (String) mapElement.getKey() + "=" + (String) mapElement.getValue();
            }
        }
        return urlPath;
    }

    /**
     * Creates the query string for timestamp.
     * @return Query string timestamp
     */
    private String getTimestamp(){
        return "timestamp=" + String.valueOf(System.currentTimeMillis());
    }

    private ResponseEntity<String> send(String urlPath, String httpMethod) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        WalletAccountSnapshotDto walletSnapshotDto;
        ResponseEntity<String> response;
        HttpHeaders headers = new HttpHeaders();
        //add API_KEY to header content
        headers.set("X-MBX-APIKEY", apiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        String urlTemplate = UriComponentsBuilder.fromHttpUrl(urlPath).q
        try {
             response = restTemplate.getForEntity(urlPath, String.class);
//             response = restTemplate.exchange(urlPath,
//                     httpMethod,
//                     requestEntity,
//                     String.class);

             logger.info("jsonResponse: \n" + response.getBody());
        } catch(Exception e){
            logger.error("Failed to get Binance info.", e);
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Map responseBody to DTO
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.getBody());
        logger.info("JsonNode: \n" + jsonResponse);
        return response;
//        walletSnapshotDto =

    }

    public ResponseEntity<String> sendSignedRequest(HashMap<String, String> parameters, String urlPath,
                                                     String httpMethod) throws Exception {
        String queryPath = "";
        String signature = "";
        this.parameters = parameters;

        if (!parameters.isEmpty()) {
            queryPath += joinQueryParameters(parameters) + "&" + getTimestamp();
        } else {
            queryPath += getTimestamp();
        }
        try {
            signature = sign.getSignature(queryPath, apiSecret);
        } catch (Exception e) {
            System.out.println("Please Ensure Your Secret Key Is Set Up Correctly! " + e);
            System.exit(0);
        }
        queryPath += "&signature=" + signature;
        final String targetUrl = baseUrl + urlPath + "?" + queryPath;
        URL obj = new URL(baseUrl + urlPath + "?" + queryPath);
        System.out.println("url:" + obj.toString());

        return send(targetUrl, httpMethod);
    }
}
