package org.example.echobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@Data
public class VkMessage {
    @JsonProperty("text")
    private String text;

    @JsonProperty("peer_id")
    private Integer peerId;


}
