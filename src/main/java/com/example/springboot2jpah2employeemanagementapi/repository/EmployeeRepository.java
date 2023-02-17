package com.example.springboot2jpah2employeemanagementapi.repository;

import com.example.springboot2jpah2employeemanagementapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
