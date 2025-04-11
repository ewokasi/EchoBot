package org.example.echobot.dto;

public enum VkEventType {
    CONFIRMATION("confirmation"),
    MESSAGE_NEW("message_new"),
    UNKNOWN("unknown");

    private final String type;

    VkEventType(String type) {
        this.type = type;
    }

    public static VkEventType fromString(String type) {
        if (type == null) return UNKNOWN;

        for (VkEventType eventType : VkEventType.values()) {
            if (eventType.type.equalsIgnoreCase(type)) {
                return eventType;
            }
        }
        return UNKNOWN;
    }

    public String getType() {
        return type;
    }
}