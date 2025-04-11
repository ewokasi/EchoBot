package org.example.echobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VkCallbackRequest {
    private String type;
    private Long groupId;

    @JsonProperty("object")
    private VkCallbackObject object;  // Объект для поля object из JSON

    public VkEventType getEventType() {
        return VkEventType.fromString(this.type);
    }
}