package com.betuiasi.server.websocket.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessageDataModel {
    @JsonProperty("timestamp")
    Long timestamp;
    @JsonProperty("username")
    String username;
    @JsonProperty("messageBody")
    String messageBody;
}
