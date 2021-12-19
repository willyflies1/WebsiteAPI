package com.api.website.controllers;

import com.api.website.models.CandlestickData;
import com.api.website.models.TickerPriceChangeStatistics;
import com.api.website.modelsDto.CandlestickDataDto;
import com.api.website.modelsDto.TickerPriceChangeStatisticsDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BinanceController {

    final String baseUri = "https://api.binance.com/api/v3";
    @Autowired
    ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/ticker/24hr")
    @ResponseBody
    public ResponseEntity<TickerPriceChangeStatisticsDto> getTickerRolling24Hr(
            @RequestParam(name = "symbol", required = false) String symbol
    ) {
        String targetUri = baseUri + "/ticker/24hr";
        // Add optional values to targerUri
        if (symbol != null) {
            targetUri += "?symbol=" + symbol;
        }
        logger.info("Target URI: " + targetUri);

        // Setup request
        RestTemplate restTemplate = new RestTemplate();
        // Get data from binance
        TickerPriceChangeStatisticsDto rolling24HrTickerDto;
        try {
            rolling24HrTickerDto = restTemplate.getForObject(targetUri,
                    TickerPriceChangeStatisticsDto.class);
            logger.info("Succesfully received response from binance api with object", rolling24HrTickerDto);

        } catch (HttpMessageConversionException exc) {
            logger.info("Failed to get correct response from binance api", targetUri, exc);
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

    @GetMapping("/klines")
    @ResponseBody
    public ResponseEntity<List<CandlestickData>> getCSData(@RequestParam(name = "symbol") String symbol,
                                                           @RequestParam(name = "interval") String interval,
                                                           @RequestParam(name = "limit", required = false,
                                                                   defaultValue = "500") String limit) {
        String targetUri = baseUri + "/klines?symbol=" + symbol + "&interval=" + interval;
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
}
