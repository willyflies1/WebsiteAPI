package com.api.website.modelsDto;

import com.api.website.models.PoolStatatistics;

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
