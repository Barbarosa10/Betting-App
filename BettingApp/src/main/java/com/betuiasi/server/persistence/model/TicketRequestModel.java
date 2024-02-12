package com.betuiasi.server.persistence.model;

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
public class TicketRequestModel {
    @JsonProperty("userUid")
    String userUid;
    @JsonProperty("matches")
    List<SelectedMatch>  matches;
    @JsonProperty("bet")
    Double bet;
    @JsonProperty("potentialWin")
    Double potentialWin;
}
