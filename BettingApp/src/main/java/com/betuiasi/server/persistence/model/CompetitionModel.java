package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitionModel {
    @JsonProperty("id")
    Long id;
    @JsonProperty("area")
    AreaModel area;
    @JsonProperty("name")
    String name;
    @JsonProperty("code")
    String code;
    @JsonProperty("type")
    String type;
    @JsonProperty("emblem")
    String emblem;

    //ignorate

    @JsonIgnore
    @JsonProperty("plan")
    String plan;
    @JsonIgnore
    @JsonProperty("currentSeason")
    Object currentSeason;
    @JsonIgnore
    @JsonProperty("numberOfAvailableSeasons")
    int numberOfAvailableSeasons;
    @JsonIgnore
    @JsonProperty("lastUpdated")
    String lastUpdated;
}
