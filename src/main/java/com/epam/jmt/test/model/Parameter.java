package com.epam.jmt.test.model;

public class Parameter {

    private final String key;
    private final int startAt;
    private final int endAt;

    public Parameter(String key, int startAt, int endAt) {
        this.key = key;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public String getKey() {
        return key;
    }

    public int getStartAt() {
        return startAt;
    }

    public int getEndAt() {
        return endAt;
    }
}
