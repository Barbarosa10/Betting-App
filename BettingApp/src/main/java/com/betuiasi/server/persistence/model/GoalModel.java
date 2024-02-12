package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoalModel {
    @JsonProperty("minute")
    int minute;
    @JsonProperty("injuryTime")
    String injuryTime;
    @JsonProperty("type")
    String type;
    @JsonProperty("team")
    TeamShortModel teamShort;
    @JsonProperty("scorer")
    ScorerModel scorer;
    @JsonIgnore
    @JsonProperty("assist")
    Object assist;
    @JsonProperty("score")
    TimeModel score;



}
