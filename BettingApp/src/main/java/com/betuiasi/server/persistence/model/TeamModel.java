package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamModel {
    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
    @JsonIgnore
    @JsonProperty("shortName")
    String shortName;
    @JsonProperty("tla")
    String tla;
    @JsonProperty("crest")
    String crest;
    @JsonProperty("address")
    Object address;
    @JsonProperty("website")
    Object website;
    @JsonProperty("founded")
    Object founded;
    @JsonProperty("clubColors")
    Object clubColors;
    @JsonProperty("venue")
    Object venue;
    @JsonProperty("lastUpdated")
    Object lastUpdated;
}
