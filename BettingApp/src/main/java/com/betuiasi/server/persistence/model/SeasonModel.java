package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeasonModel {
    @JsonProperty("id")
    Long id;
    @JsonProperty("startDate")
    String startDate;
    @JsonProperty("endDate")
    String endDate;
    @JsonProperty("currentMatchday")
    int currentMatchday;
    @JsonProperty("winner")
    Object winner;
    @JsonProperty("stages")
    List<Object> stages;
}
