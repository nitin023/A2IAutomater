package com.a2i.demo.service;

import com.a2i.demo.DTO.InspectionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Service
public class RestService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public InspectionResponse getInspectionData() throws IOException {
        InspectionResponse inspectionResponse = convertJsonToTarget("contactService.json", InspectionResponse.class);
        return inspectionResponse;
    }

    /**
     * Replace with API call
     *
     * @param fileName
     * @param dataClass
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T> T convertJsonToTarget(String fileName, Class<T> dataClass) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return objectMapper.readValue(file,dataClass);
    }

}
