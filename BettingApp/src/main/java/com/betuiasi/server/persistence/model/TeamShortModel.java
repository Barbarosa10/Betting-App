package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamShortModel {
    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
}
