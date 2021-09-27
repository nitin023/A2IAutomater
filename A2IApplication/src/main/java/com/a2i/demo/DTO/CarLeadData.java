package com.a2i.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarLeadData {

    private Integer count;

    @JsonProperty(value = "list")
    private List<InspectionDTO> inspectionDTOList;
}
