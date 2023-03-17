package com.example.springboot03022023;

import com.example.springboot03022023.domain.Employee;
import com.example.springboot03022023.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBoot03022023Application implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot03022023Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Employee employee1 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        Employee employee2 = Employee.builder()
                .name("Name1")
                .city("City1")
                .company("Company1")
                .build();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        employeeRepository.saveAll(employeeList);*/

    }
}
