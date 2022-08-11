package com.api.website.model.dto;

import com.api.website.model.SnapshotVos;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

public class WalletAccountSnapshotDto implements Serializable {
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("snapshotVos")
    private SnapshotVos[] snapshotVos;

    public WalletAccountSnapshotDto() {
    }

    public WalletAccountSnapshotDto(Integer code, String msg, SnapshotVos[] snapshotVos) {
        this.code = code;
        this.msg = msg;
        this.snapshotVos = snapshotVos;
    }

    @Override
    public String toString() {
        return "WalletAccountSnapshotDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", snapshotVos=" + Arrays.toString(snapshotVos) +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SnapshotVos[] getSnapshotVos() {
        return snapshotVos;
    }

    public void setSnapshotVos(SnapshotVos[] snapshotVos) {
        this.snapshotVos = snapshotVos;
    }
}
