package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamsModel {
    @JsonIgnore
    @JsonProperty("count")
    Object count;
    @JsonIgnore
    @JsonProperty("filters")
    Object filters;
    @JsonProperty("teams")
    List<TeamModel> teams;
}
