package com.betuiasi.server.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AreaModel {
    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
    @JsonProperty("code")
    String code;
    @JsonProperty("flag")
    String flag;
    @JsonIgnore
    @JsonProperty("parentAreaId")
    int parentAreaId;
    @JsonIgnore
    @JsonProperty("parentArea")
    String parentArea;
}
