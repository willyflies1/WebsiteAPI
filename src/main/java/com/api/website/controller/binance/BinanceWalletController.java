package com.api.website.controller.binance;

import com.api.website.controller.LoggingController;
import com.api.website.model.dto.*;
import com.api.website.model.CandlestickData;
import com.api.website.model.SnapshotType;
import com.api.website.model.TickerPriceChangeStatistics;
import com.api.website.service.Request;
import com.binance.connector.client.impl.SpotClientImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class BinanceWalletController {

    //    private static String BINANCE_API_KEY = System.getenv("BINANCE_API_KEY");
//    private static String BINANCE_SECRET_KEY = System.getenv("BINANCE_SECRET_KEY");
    private static String BINANCE_API_KEY = "kgT3EzNfFEEFQtiqTbSLrdfbXkMiFvHR2XbF7Z0GKCfiMa7ZN2uLU7ZuPddFfhCW";
    private static String BINANCE_SECRET_KEY = "c3nRnjd5qUaa0r7eE528IGPTeud0CpIm3KlS208LyNMm20P5Ub1bTzWYmTcsQhxe";
    final String baseUrl = "https://api.binance.com";
    final String baseUrlUS = "https://api.binance.us";
    final String testBaseUrl = "https://testnet.binance.vision";    // /api/v3
    //    final String signedBaseUrl = "https://api.binance.com/sapi/v1";
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<String, Object>();
    Request request;

    @Autowired
    ModelMapper modelMapper;

    ObjectMapper mapper = new ObjectMapper();


    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("wallet/accountSnapshot")
    @ResponseBody
    public ResponseEntity<WalletAccountSnapshotDto> getAccountSnapshot(
            @RequestParam(name = "type", required = true, defaultValue = "SPOT") String type,
            @RequestParam(name = "startTime", required = false) Optional<Long> startTime,
            @RequestParam(name = "endTime", required = false) Optional<Long> endTime,
            @RequestParam(name = "limit", required = false) Optional<Integer> limit,
            @RequestParam(name = "recvWindow", required = false) @Min(5) @Max(30) Optional<Long> recvWindow
    ) {
        // - type
        if (!EnumUtils.isValidEnum(SnapshotType.class, type)) {
            return new ResponseEntity("Type parameter needs to be value 'SPOT', 'MARGIN' or 'FUTURE'",
                    HttpStatus.BAD_REQUEST);
        }

        // ** Create Uri to get binance wallet info
        parameters.put("type", type);
        if (!startTime.isEmpty()) parameters.put("startTime", startTime.get().toString());
        if (!endTime.isEmpty()) parameters.put("endTime", endTime.get().toString());
        if (!limit.isEmpty()) parameters.put("limit", limit.get().toString());
        if (!recvWindow.isEmpty())
            parameters.put("recvWindow", recvWindow.get().toString());    // Default is 5000 on Binance side
        ResponseEntity<WalletAccountSnapshotDto> binanceResponse;
        try {
            // Binance Connector
            SpotClientImpl client = new SpotClientImpl(BINANCE_API_KEY, BINANCE_SECRET_KEY, baseUrlUS);
            String response = client.createWallet().accountSnapshot(parameters);
            parameters.clear();
            String allCoinsResponse = client.createWallet().depositHistory(parameters);
            logger.info("Deposit History" + allCoinsResponse);
            // Map Object for response
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(response, Map.class);
            WalletAccountSnapshotDto dto = mapper.readValue(response, WalletAccountSnapshotDto.class);
            logger.info("Response collected. Sending: " + dto.toString());
            binanceResponse = new ResponseEntity<WalletAccountSnapshotDto>(dto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Failed to collect response", e);
            return new ResponseEntity<WalletAccountSnapshotDto>(new WalletAccountSnapshotDto(), HttpStatus.BAD_REQUEST);
        }
        parameters.clear();
        return binanceResponse;
    }

    @GetMapping("/capital/deposit/hisrec")
    @ResponseBody
    public ResponseEntity<DepositHistoryCollectionDto> getDepositHistory(
            @RequestParam(name = "coin", required = false) Optional<String> coin,
            @RequestParam(name = "status", required = false) Optional<Integer> status,
            @RequestParam(name = "startTime", required = false) Optional<Long> startTime,
            @RequestParam(name = "endTime", required = false) Optional<Long> endTime,
            @RequestParam(name = "offset", required = false) Optional<Integer> offset,
            @RequestParam(name = "limit", required = false) Optional<Integer> limit,
            @RequestParam(name = "recvWindow", required = false) Optional<Long> recvWindow
    ) {
        ResponseEntity<DepositHistoryCollectionDto> binanceResponse = null;

        // ** Add parameters to LinkedHashMap
        if (!coin.isEmpty()) parameters.put("coin", coin.get());
        if (!status.isEmpty()) parameters.put("status", status.get());
        if (!startTime.isEmpty()) parameters.put("startTime", startTime.get());
        if (!endTime.isEmpty()) parameters.put("endTime", endTime.get());
        if (!offset.isEmpty()) parameters.put("offset", offset.get());
        if (!limit.isEmpty()) parameters.put("limit", limit.get());
        if (!recvWindow.isEmpty()) parameters.put("recvWindow", recvWindow.get());

        // ** Get data from Binance endpoint
        try {
            SpotClientImpl client = new SpotClientImpl(BINANCE_API_KEY, BINANCE_SECRET_KEY, baseUrlUS);
            String response = client.createWallet().depositHistory(parameters);
            logger.info("Deposit History (Response): \n" + response);
            modelMapper = new ModelMapper();
            ArrayList<DepositHistoryDto> list = mapper.readValue(response,
                    ArrayList.class);
            DepositHistoryCollectionDto dto = modelMapper.map(response, DepositHistoryCollectionDto.class);
            dto.setDeposits(list);
            binanceResponse = new ResponseEntity<DepositHistoryCollectionDto>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // ** Add to Db if needed

        // ** Send response back on success
        return binanceResponse != null
                ? binanceResponse
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/ticker/24hr")
    @ResponseBody
    public ResponseEntity<TickerPriceChangeStatisticsDto> getTickerRolling24Hr(
            @RequestParam(name = "symbol", required = false) String symbol
    ) {
        String targetUrl = baseUrl + "/api/v3/ticker/24hr";
        // Add optional values to targerUri
        if (symbol != null) {
            targetUrl += "?symbol=" + symbol;
        }
        logger.info("Target URI: " + targetUrl);

        // Setup request
        RestTemplate restTemplate = new RestTemplate();
        // Get data from binance
        TickerPriceChangeStatisticsDto rolling24HrTickerDto;
        try {
            rolling24HrTickerDto = restTemplate.getForObject(targetUrl,
                    TickerPriceChangeStatisticsDto.class);
            logger.info("Succesfully received response from binance api with object", rolling24HrTickerDto);

        } catch (HttpMessageConversionException exc) {
            logger.info("Failed to get correct response from binance api", targetUrl, exc);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Trouble building response from " +
                    "Binance", exc);
        }

        // ** Transfer DtO to Entity
        TickerPriceChangeStatistics rolling24HrTickerEntity = convertRollingTickerToEntity(rolling24HrTickerDto);
        logger.info("Rolling 24 Hr Ticker Entity Created");
        logger.info(rolling24HrTickerEntity.toString());

        // ** Store Entity in Database

        // ** Return DataTransferObject
        return new ResponseEntity<TickerPriceChangeStatisticsDto>(rolling24HrTickerDto, HttpStatus.OK);

    }

    @GetMapping("/klines")
    @ResponseBody
    public ResponseEntity<List<CandlestickData>> getCSData(@RequestParam(name = "symbol") String symbol,
                                                           @RequestParam(name = "interval") String interval,
                                                           @RequestParam(name = "limit", required = false,
                                                                   defaultValue = "500") String limit) {
        String targetUri = baseUrl + "api/v3/klines?symbol=" + symbol + "&interval=" + interval;
        // Add optional if in request
        if (limit != null) {
            targetUri += "&limit=" + limit;
        }

        RestTemplate restTemplate = new RestTemplate();

        // ** Get response from Binance with Candlestick Data
        ResponseEntity<String[][]> cSDataResponse;
        List<CandlestickDataDto> candlestickDataModels = new ArrayList<>();
        List<CandlestickData> responseBody = new ArrayList<>();
        try {
            cSDataResponse = restTemplate.getForEntity(targetUri, String[][].class);
            // If bad response from Binance API
            if (cSDataResponse.getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<List<CandlestickData>>(new ArrayList<CandlestickData>(), HttpStatus.BAD_REQUEST);
            }

            // ** Create list of Dto Models from string[][]
            candlestickDataModels = buildCandlestickDataList(cSDataResponse.getBody());

            // ** Transfer DtO to Entity
            responseBody = candlestickDataModels.stream().map(dto -> {
                return new CandlestickData(dto.getOpenTime(), dto.getOpen(), dto.getHigh(), dto.getLow(),
                        dto.getClose(),
                        dto.getNumberOfTrades(), dto.getCloseTime(), dto.getVolume());
            }).collect(Collectors.toList());

        } catch (Exception exc) {
            logger.info("Exception thrown", exc);
        }

        // ** Return ResponseEntity<List<Dto>>
        return !responseBody.isEmpty()
                ? new ResponseEntity<List<CandlestickData>>(responseBody, HttpStatus.OK)
                : new ResponseEntity<List<CandlestickData>>(responseBody, HttpStatus.BAD_REQUEST);
    }

    // ** Methods

    private TickerPriceChangeStatisticsDto convertRollingTickerToDto(TickerPriceChangeStatistics tickerEntity) {
//        TickerPriceChangeStatisticsDto tickerDto = modelMapper().map(tickerEntity,
//                TickerPriceChangeStatisticsDto.class);
        return modelMapper.map(tickerEntity,
                TickerPriceChangeStatisticsDto.class);
    }

    private TickerPriceChangeStatistics convertRollingTickerToEntity(TickerPriceChangeStatisticsDto tickerDto) {
        TickerPriceChangeStatistics tickerEntity = modelMapper.map(tickerDto, TickerPriceChangeStatistics.class);
        return tickerEntity;
    }

    private List<CandlestickDataDto> buildCandlestickDataList(String[][] csDataResponse) {
        List<CandlestickDataDto> candlestickDataModels = new ArrayList<CandlestickDataDto>();
        Arrays.stream(csDataResponse).forEach(candleStickData -> {
                    final CandlestickDataDto newModel = new CandlestickDataDto(
                            Double.parseDouble(candleStickData[0]),
                            Double.parseDouble(candleStickData[1]),
                            Double.parseDouble(candleStickData[2]),
                            Double.parseDouble(candleStickData[3]),
                            Double.parseDouble(candleStickData[4]),
                            Double.parseDouble(candleStickData[5]),
                            Double.parseDouble(candleStickData[6]),
                            Double.parseDouble(candleStickData[7]),
                            Integer.parseInt(candleStickData[8]),
                            Double.parseDouble(candleStickData[9]),
                            Double.parseDouble(candleStickData[10]),
                            Double.parseDouble(candleStickData[11])
                    );
                    candlestickDataModels.add(newModel);
//                    System.out.println(candlestickDataModels.get(candlestickDataModels.size()).toString());
                }
        );
        return candlestickDataModels;
    }

    /**
     * Encodes a String value to a HmacSHA256
     *
     * @param value String to encode
     * @return HmacSHA256 encoded string
     */
    private String encodeHmacSHA256(String value) {
        logger.info("Encoded String as Bytes[]=" + Hex.encode(value.getBytes()));
        byte[] hmacsha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(BINANCE_API_KEY.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            hmacsha256 = mac.doFinal(value.getBytes());
            logger.info("Encoded string as bytes[] using Mac: " + hmacsha256);

            return new String(Hex.encode(hmacsha256));

        } catch (Exception exc) {
            throw new RuntimeException("Failed to calculate hmac-sha256", exc);
        }
//        return new String(Hex.encode(value.getBytes()));
    }

    /**
     * Decodes a HmacSHA256 value to a readable string
     *
     * @param value HmacSHA256 value to decode
     * @return Decoded HmacSHA256 value
     */
    private String decodeHmacSHA256(String value) {
        return new String(Hex.decode(value.subSequence(0, value.length() - 1)));
    }

    /**
     * Creates the query string for timestamp.
     *
     * @return Query string timestamp
     */
    private String getTimestamp() {
        return "timestamp=" + String.valueOf(System.currentTimeMillis());
    }


}
