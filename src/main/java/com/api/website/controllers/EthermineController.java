package com.api.website.controllers;

import com.api.website.models.MinerDashboard;
import com.api.website.models.PoolStatatistics;
import com.api.website.modelsDto.MinerDashboardDto;
import com.api.website.modelsDto.PoolStatsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EthermineController {
    final String baseUri = "https://api.ethermine.org";


    @GetMapping("/ethereum/poolStats")
    public PoolStatatistics getPoolStats(){
        // -----WORKS------
        String targetUri = baseUri + "/poolStats";
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PoolStatsDto> poolStatsDto = restTemplate.getForEntity(targetUri, PoolStatsDto.class);
        final PoolStatatistics resultingPoolStats = poolStatsDto.getBody().getData();
        // ----------------
        final HashMap<String, String> responseData = (HashMap<String, String>) restTemplate.getForObject(targetUri, HashMap.class).get("data");
        final Map<String, String> mapData = (HashMap<String, String>) restTemplate.getForObject(targetUri, HashMap.class).get("data");
        for (Map.Entry pairEntry: mapData.entrySet()) {
            System.out.println(pairEntry.getKey() + " : " + pairEntry.getValue());
        }

//        System.out.println("Response Data...");
//        System.out.println(responseData);

        String response = restTemplate.getForObject(targetUri, String.class);
        return resultingPoolStats;
    }

    @GetMapping("/ethereum/miner/{miner}/dashboard")
    public @ResponseBody
    ResponseEntity<MinerDashboard> getMinerDashboard(@PathVariable String miner){
        String targetUri = baseUri + "/miner/" + miner + "/dashboard";
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<MinerDashboardDto> minerDashboardDto = restTemplate.getForEntity(targetUri, MinerDashboardDto.class);
        final MinerDashboard minerDashboard = minerDashboardDto.getBody().getData();
        return minerDashboard != null
            ? new ResponseEntity<MinerDashboard>(minerDashboard, HttpStatus.OK)
            : new ResponseEntity<MinerDashboard>(minerDashboard, HttpStatus.BAD_REQUEST);
    }
}