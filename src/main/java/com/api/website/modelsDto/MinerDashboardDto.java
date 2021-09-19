package com.api.website.modelsDto;

import com.api.website.models.MinerDashboard;

public class MinerDashboardDto {
    private String status;
    private MinerDashboard data;

    public MinerDashboardDto(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MinerDashboard getData() {
        return data;
    }

    public void setData(MinerDashboard data) {
        this.data = data;
    }
}
