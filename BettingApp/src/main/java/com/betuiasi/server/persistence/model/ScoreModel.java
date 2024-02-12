package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScoreModel {
    @Indexed
    @JsonProperty("id")
    String id;
    @JsonProperty("winner")
    String winner;
    @JsonProperty("duration")
    String duration;
    @JsonProperty("fullTime")
    TimeModel fullTime;
    @JsonProperty("halfTime")
    TimeModel halfTime;
    @JsonProperty("goals")
    List<GoalModel> goals;
}
