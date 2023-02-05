package com.example.springboot03022023.service;

import com.example.springboot03022023.domain.Employee;
import com.example.springboot03022023.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(final Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new
                RuntimeException(String.format("Der Employee %d ist nicht vorhanden", id)));
    }

    public Employee saveEmployee(final Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }
}
