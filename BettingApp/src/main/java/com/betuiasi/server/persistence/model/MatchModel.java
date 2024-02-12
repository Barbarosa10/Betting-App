package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchModel {
    @JsonProperty("area")
    AreaModel area;
    @JsonProperty("competition")
    CompetitionModel competition;
    @JsonProperty("season")
    SeasonModel season;
    @JsonProperty("id")
    Long id;
    @JsonProperty("sportType")
    String sportType;
    @JsonProperty("utcDate")
    String utcDate;
    @JsonProperty("status")
    String status;
    @JsonProperty("minute")
    String minute;
    @JsonProperty("injuryTime")
    String injuryTime;
    @JsonIgnore
    @JsonProperty("attendance")
    Object attendance;
    @JsonProperty("matchday")
    int matchday;
    @JsonProperty("stage")
    String stage;
    @JsonProperty("group")
    String group;
    @JsonProperty("lastUpdated")
    String lastUpdated;
    @JsonProperty("homeTeam")
    TeamModel homeTeam;
    @JsonProperty("awayTeam")
    TeamModel awayTeam;
    @JsonProperty("score")
    ScoreModel score;
    @JsonProperty("goals")
    List<GoalModel> goals;
    @JsonIgnore
    @JsonProperty("penalties")
    Object penalties;
    @JsonIgnore
    @JsonProperty("substitutions")
    Object substitutions;
    @JsonIgnore
    @JsonProperty("odds")
    Object odds;
    @JsonIgnore
    @JsonProperty("referees")
    Object referees;
    @JsonProperty("odds_generated")
    OddsModel odds_generated;

}
