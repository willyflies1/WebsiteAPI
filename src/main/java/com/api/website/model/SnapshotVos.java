package com.api.website.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnapshotVos {
    @JsonProperty("data")
    private SnapshotData data;

    @JsonProperty("type")
    private String type;

    @JsonProperty("updateTime")
    private Double updateTime;

    public SnapshotVos() {
    }

    public SnapshotVos(SnapshotData data, String type, Double updateTime) {
        this.data = data;
        this.type = type;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SnapshotVos{" +
                "data=" + data +
                ", type='" + type + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public SnapshotData getData() {
        return data;
    }

    public void setData(SnapshotData data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Double updateTime) {
        this.updateTime = updateTime;
    }
}
