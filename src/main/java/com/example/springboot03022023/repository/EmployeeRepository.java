package com.example.springboot03022023.repository;

import com.example.springboot03022023.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
