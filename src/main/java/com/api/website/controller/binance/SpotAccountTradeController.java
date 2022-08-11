package com.api.website.controller.binance;

import com.api.website.controller.LoggingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class SpotAccountTradeController {

    private static String BINANCE_API_KEY = "kgT3EzNfFEEFQtiqTbSLrdfbXkMiFvHR2XbF7Z0GKCfiMa7ZN2uLU7ZuPddFfhCW";
    private static String BINANCE_SECRET_KEY = "c3nRnjd5qUaa0r7eE528IGPTeud0CpIm3KlS208LyNMm20P5Ub1bTzWYmTcsQhxe";
    final String baseUrl = "https://api.binance.com";
    final String baseUrlUS = "https://api.binance.us";
    final String testBaseUrl = "https://testnet.binance.vision";    // /api/v3
    private LinkedHashMap<String, Object> parameters = new LinkedHashMap<String, Object>();
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(LoggingController.class);

//    @GetMapping("spot/account")
//    @ResponseBody
//    public ResponseEntity<String> getAccountInformation(
//            @RequestParam(name = "recvWindow", required = false) Optional<Long> recvWindow
//    ) {
//        // Local variables
//        ResponseEntity<String> binanceResponse = null;
//
//        // ** Add parameters
//        if (!recvWindow.isEmpty()) parameters.put("recvWindow", recvWindow.get().toString());
//
//        // ** Get Binance response
//        try{
//            SpotClientImpl client = new SpotClientImpl(BINANCE_API_KEY, BINANCE_SECRET_KEY, baseUrlUS);
//            String response = client.createTrade().account(parameters);
//            // String to dto
//            AccountInformationDto accInfoDto = mapper.readValue(response, AccountInformationDto.class);
//            logger.info("Map String to AccountInformationDto:\n" + accInfoDto.toString());
//            // Needs ModelMapper init()
////            AccountInformtaionDto accInfoDto = mapper.map(response, AccountInformtaionDto.class);
//
//            binanceResponse = new ResponseEntity<String>(response, HttpStatus.OK);
//            logger.info("Response createWallet().coinInfo():\n" + response);
//        } catch(Exception e){
//            logger.error("Failed to collect Binance response:\n", e);
//        }
//
//        // ** Send response back on success
//        return binanceResponse != null
//                ? binanceResponse
//                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
