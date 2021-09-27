package com.a2i.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionData {

    @JsonProperty(value = "carLeadCallback")
    private CarLeadData carLeadData;
}
