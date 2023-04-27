package com.uWinOPCTjyx.entity;

public class PreviewPdfJson {

    private String uuid;
    private String data;

    public PreviewPdfJson(String uuid, String data) {
        this.uuid = uuid;
        this.data = data;
    }

    public PreviewPdfJson() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
