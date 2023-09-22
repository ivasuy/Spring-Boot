package com.vasu.Springboot.tutorial.controller;

import com.vasu.Springboot.tutorial.entity.Department;
import com.vasu.Springboot.tutorial.error.DepartmentNotFoundException;
import com.vasu.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Meerut")
                .departmentCode("IT-61")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Solan")
                .departmentCode("IT-69")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON)
                .content("\t{\n" +
                        "\t\t\"departmentId\": 1,\n" +
                        "\t\t\"departmentName\": \"IT\",\n" +
                        "\t\t\"departmentAddress\": \"Solan\",\n" +
                        "\t\t\"departmentCode\": \"IT-69\"\n" +
                        "\t}")).andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1l)).thenReturn(department);
        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                .value(department.getDepartmentName()));
    }
}