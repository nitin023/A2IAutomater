package com.twitter.demo.Services;


import com.twitter.demo.DTO.InspectionDTO;
import com.twitter.demo.DTO.InspectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Service
public class InspectionTaskService {

    @Autowired
    private RestService restService;

    public void inspect() {
        List<InspectionDTO> inspectionDTOList = fetchAllInspections();
        if(!CollectionUtils.isEmpty(inspectionDTOList)){
                for (InspectionDTO inspectionDTO : inspectionDTOList){
                    System.out.println(inspectionDTO);
                }
        }
    }

    private List<InspectionDTO> fetchAllInspections() {
        List<InspectionDTO> inspectionDTOList = null;
        try {
            InspectionResponse inspectionResponse = restService.getInspectionData();
            if(null != inspectionResponse){
                System.out.println(inspectionResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inspectionDTOList;
    }
}
