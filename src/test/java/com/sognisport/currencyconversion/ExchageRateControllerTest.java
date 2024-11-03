package com.sognisport.currencyconversion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchageRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired

    @Test
    public void whenJsonConverterIsFound_thenReturnResponse() throws Exception {
        String url = "/api/student/v2/1";

//        mockMvc.perform("/api/exchange")
//                .andExpect(status().isOk())
//                .andExpect(content().json("{'id':1,'firstName':'Kevin','lastName':'Cruyff', 'grade':'AA'}"));
    }
}
