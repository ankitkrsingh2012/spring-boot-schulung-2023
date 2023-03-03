package com.example.springboot03022023.controller;

import com.example.springboot03022023.domain.Employee;
import com.example.springboot03022023.repository.EmployeeRepository;
import com.example.springboot03022023.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@Slf4j
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void testeGetEmployees() throws Exception {
        Employee employee1 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        Employee employee2 = Employee.builder()
                .name("Name2")
                .city("City2")
                .company("Company2")
                .build();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeService.getEmployees()).thenReturn(employeeList);

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].name").value("Name1"))
                .andExpect(jsonPath("$[0].city").value("City1"))
                .andExpect(jsonPath("$[0].company").value("Company1"))
                .andExpect(jsonPath("$[1].name").value("Name2"))
                .andExpect(jsonPath("$[1].city").value("City2"))
                .andExpect(jsonPath("$[1].company").value("Company2"));
    }

    @Test
    void testeGetEmployee() throws Exception {
        Employee employee1 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        when(employeeService.getEmployee(1L)).thenReturn(employee1);

        mockMvc.perform(get("/employee/1"))
                .andDo(print())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Name1"))
                .andExpect(jsonPath("$.city").value("City1"))
                .andExpect(jsonPath("$.company").value("Company1"));
    }

    @Test
    void testeSaveEmployee() throws Exception {
        Employee employee1 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        String employeeAsString = new ObjectMapper().writeValueAsString(employee1);

        log.info(employeeAsString);
        log.info(String.valueOf(employee1));

        when(employeeService.saveEmployee(employee1)).thenReturn(employee1);

        mockMvc.perform(post("/employee")
                        .content(employeeAsString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Name1"))
                .andExpect(jsonPath("$.city").value("City1"))
                .andExpect(jsonPath("$.company").value("Company1"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employee/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
