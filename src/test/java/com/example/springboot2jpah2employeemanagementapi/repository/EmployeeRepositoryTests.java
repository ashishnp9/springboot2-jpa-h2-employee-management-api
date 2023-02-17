package com.example.springboot2jpah2employeemanagementapi.repository;

import com.example.springboot2jpah2employeemanagementapi.Springboot2JpaH2EmployeeManagementApiApplication;
import com.example.springboot2jpah2employeemanagementapi.model.Employee;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot2JpaH2EmployeeManagementApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static Employee employee;

    @BeforeClass
    public static void setup() {
        employee = Employee.builder()
                .firstName("Ashish")
                .lastName("Patel")
                .emailId("ashish@gmail,com")
                .build();
    }

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ashish")
                .lastName("Patel")
                .emailId("ashish@gmail,com")
                .build();
        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertNotNull(savedEmployee);
        //assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertTrue(savedEmployee.getId() > 0);
    }


    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Cena")
                .emailId("cena@gmail,com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when -  action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertNotNull(employeeList);
        assertEquals(employeeList.size(), 2);

    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertNotNull(employeeDB);
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmailId("ram@gmail.com");
        savedEmployee.setFirstName("Ram");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertEquals(updatedEmployee.getFirstName(), "Ram");
        assertEquals(updatedEmployee.getEmailId(), "ram@gmail.com");
    }

    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertTrue(employeeOptional.isEmpty());

    }
}

