package com.example.countryborders.controllers;

import com.example.countryborders.exceptions.RouteNotFoundException;
import com.example.countryborders.services.impl.RouteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RouteServiceImpl routeService;

    @Test
    public void shouldReturnRoutesFromService() throws RouteNotFoundException, Exception {
        when(routeService.getRoutesForBorders(anyString(),anyString())).thenReturn(Set.of("1","2"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/routing/{origin}/{destination}",
                        "1","2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
