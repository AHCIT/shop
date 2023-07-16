package com.example.actionserver.enums;

public enum UserActionEnum {
    LIKE("like", 0),
    COLLECT("collect", 1),
    TRANSMIT("transmit", 2),
    SCAN("scan", 3);

    private final String action;
    private final Integer type;

    UserActionEnum(String action, Integer type) {
        this.action = action;
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public Integer getType() {
        return type;
    }
}
