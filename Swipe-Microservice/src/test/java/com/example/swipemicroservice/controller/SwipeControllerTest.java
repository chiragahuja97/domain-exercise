package com.example.swipemicroservice.controller;

import com.example.swipemicroservice.entity.AttendanceDetails;
import com.example.swipemicroservice.entity.Employee;
import com.example.swipemicroservice.entity.SwipeDetails;
import com.example.swipemicroservice.service.EmployeeService;
import com.example.swipemicroservice.service.SwipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(SwipeController.class)
class SwipeControllerTest {

    @MockBean
    SwipeService swipeService;

    @MockBean
    EmployeeService empservice;

    @Autowired
    MockMvc mockMvc;



    @Test
    void swipe() throws Exception{

        Mockito.when(swipeService.swipe(ArgumentMatchers.any())).thenReturn(new SwipeDetails());
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/swipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(new SwipeDetails()))).
                andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void getAttandance() throws Exception {

        Mockito.when(swipeService.findByUserIdAndDate(ArgumentMatchers.any(),ArgumentMatchers.anyString())).thenReturn(new ArrayList<SwipeDetails>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/attendance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(new SwipeDetails()))).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createEmployee() throws Exception {

        Mockito.when(empservice.getLastRecord()).thenReturn(new ArrayList<Employee>());
        Mockito.when(empservice.saveEmployee(ArgumentMatchers.any())).thenReturn(new Employee());
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(new Employee()))).
                andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getEmployees() throws Exception {
        Mockito.when(empservice.findAll()).thenReturn(new ArrayList<Employee>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        ).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}