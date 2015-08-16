package com.kingofthehill.model;

public enum GrafitiStatus {
    CREATED("CREATED"), CURRENT("CURRENT"), COMPLETED("COMPLETED");

    private String status;

    private GrafitiStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
