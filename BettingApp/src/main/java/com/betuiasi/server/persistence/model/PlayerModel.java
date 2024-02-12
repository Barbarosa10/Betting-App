package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerModel {
    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
    @JsonProperty("position")
    String position;
    @JsonProperty("shirtNumber")
    int shirtNumber;
}
