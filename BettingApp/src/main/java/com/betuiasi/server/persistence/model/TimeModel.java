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
public class TimeModel {
    @JsonProperty("home")
    int home;
    @JsonProperty("away")
    int away;
}
