package com.asp.emission.query.api;

import com.asp.emission.entity.Sensor;
import com.asp.emission.enums.SensorStatus;
import com.asp.emission.query.dto.SensorStatusDto;
import com.asp.emission.query.service.SensorQueryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SensorQueryController.class)
public class SensorQueryControllerTest {


    @MockBean
    SensorQueryService sensorQueryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfetchSensorStatus() throws Exception {
        String uuid = "testUUID";

        SensorStatusDto sensorStatusDto = new SensorStatusDto(SensorStatus.OK.name());

        Mockito.when(sensorQueryService.fetchSensorStatus(uuid)).thenReturn(sensorStatusDto);

        mockMvc.perform(get("/api/v1/sensors/" + uuid))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.status", Matchers.is("OK")));
    }


}
