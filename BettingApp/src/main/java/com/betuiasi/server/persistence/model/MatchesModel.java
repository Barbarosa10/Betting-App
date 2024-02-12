package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchesModel {
    @JsonIgnore
    @JsonProperty("filters")
    Object filters;
    @JsonIgnore
    @JsonProperty("resultSet")
    Object resultSet;
    @JsonProperty("matches")
    List<MatchModel> matches;
}
