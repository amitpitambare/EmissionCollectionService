package com.asp.emission.command.api;

import com.asp.emission.command.dto.AddSensorMeasurementCommandDto;
import com.asp.emission.command.service.SensorCollectionService;
import com.asp.emission.enums.SensorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.URL;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SensorCollectionController.class)
public class SensorCollectionControllerTest {

    @MockBean
    SensorCollectionService sensorCollectionService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCollectionSensorMeasurement() throws Exception {
        String uuid = "testUUID";

        AddSensorMeasurementCommandDto inputDto = new AddSensorMeasurementCommandDto(1000, new Date());



        Mockito.doNothing().when(sensorCollectionService).publishSensorMeasurementEvent(uuid,inputDto);

        mockMvc.perform(post("/api/v1/sensors/" +uuid+"/measurements").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto)))
                .andExpect(status().isAccepted());
    }





}
