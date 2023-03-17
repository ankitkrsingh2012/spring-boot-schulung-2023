package com.example.springboot03022023;

import com.example.springboot03022023.domain.Employee;
import com.example.springboot03022023.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    Employee employee1 = Employee.builder()
            .id(1l)
            .name("Name1")
            .city("City1")
            .company("Company1")
            .build();
    Employee employee2 = Employee.builder()
            .id(2l)
            .name("Name2")
            .city("City2")
            .company("Company2")
            .build();
    List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeRepository.saveAll(employeeList);
    }

    @Test
    @Order(1)
    void testGetEmployees() {
        List<Employee> responseEmployeeList = Arrays.stream(
                testRestTemplate.getForObject("/employees", Employee[].class)).toList();

        assertThat(responseEmployeeList).isEqualTo(employeeList);
    }

    @Test
    @Order(2)
    void testGetEmployee() {
        Employee responseEmployee = testRestTemplate.getForObject("/employee/1", Employee.class);
        assertThat(responseEmployee).isEqualTo(employee1);
    }

    @Test
    @Order(3)
    void testSaveEmployee(){
        Employee employee3 = Employee.builder()
                .id(3l)
                .name("Name3")
                .city("City3")
                .company("Company3")
                .build();

        Employee responseEmployee = testRestTemplate.postForObject("/employee", employee3, Employee.class);
        assertThat(responseEmployee).isEqualTo(employee3);
    }

    @Test
    @Order(4)
    void deleteEmployee() {
        ResponseEntity<Employee> responseEntity = testRestTemplate
                .exchange("/employee/1", HttpMethod.DELETE, null, Employee.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
