package com.example.springboot03022023.service;

import com.example.springboot03022023.domain.Employee;
import com.example.springboot03022023.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void testeGetEmployees(){
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

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> response = employeeService.getEmployees();

        assertThat(response).hasSize(2);
        assertThat(response.get(0).getName()).isEqualTo("Name1");
        assertThat(response.get(0).getCity()).isEqualTo("City1");
        assertThat(response.get(0).getCompany()).isEqualTo("Company1");
        assertThat(response.get(1).getName()).isEqualTo("Name2");
        assertThat(response.get(1).getCity()).isEqualTo("City2");
        assertThat(response.get(1).getCompany()).isEqualTo("Company2");


        assertThat(response.get(0)).isEqualTo(employee1);
        assertThat(response.get(1)).isEqualTo(employee2);
    }

    @Test
    void testeGetEmployeesById(){
        Employee employee1 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee1));

        Employee response = employeeService.getEmployee(1L);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Name1");
        assertThat(response.getCity()).isEqualTo("City1");
        assertThat(response.getCompany()).isEqualTo("Company1");
    }

    @Test
    void testeSaveEmployee(){
        Employee employee1 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        when(employeeRepository.save(employee1)).thenReturn(employee1);

        Employee response = employeeService.saveEmployee(employee1);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Name1");
        assertThat(response.getCity()).isEqualTo("City1");
        assertThat(response.getCompany()).isEqualTo("Company1");
    }

    @Test
    void testeDeleteEmployee(){
        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }

}
