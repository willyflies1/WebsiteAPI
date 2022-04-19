package com.api.website.model.dto;

import com.api.website.model.PoolStatatistics;

public class PoolStatsDto {
    private String status;
    private PoolStatatistics data;

    public PoolStatsDto() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PoolStatatistics getData() {
        return data;
    }

    public void setData(PoolStatatistics data) {
        this.data = data;
    }
}
