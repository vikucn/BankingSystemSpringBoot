package com.springboot.banking_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.banking_system.enums.Role;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.repository.EmployeeRepository;
import com.springboot.banking_system.repository.UserRepository;
import com.springboot.banking_system.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder getEncoder;

    private Employee employee;
    private User user;

    @BeforeEach
    public void initSetup() {
        user = new User();
        user.setId(1);
        user.setRole(Role.ADMIN);
        user.setPassword("password123");

        employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDateOfBirth(LocalDate.of(1985, 5, 15));
        employee.setGender("Male");
        employee.setContactNumber("9876543210");
        employee.setAddress("123 Elm Street");
        employee.setSalary(55000.00);
        employee.setUser(user);
    }

    @Test
    public void validateEmployeeTestSuccess() throws ResourceNotFoundException {
    
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

      
        Employee validatedEmployee = employeeService.validate(1);


        assertNotNull(validatedEmployee);
        assertEquals(employee.getId(), validatedEmployee.getId());
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    public void validateEmployeeTestFailure() {
  
        when(employeeRepository.findById(2)).thenReturn(Optional.empty());


        Exception exception = assertThrows(ResourceNotFoundException.class, 
            () -> employeeService.validate(2));
        assertEquals("Given id is invalid try again...", exception.getMessage());
        verify(employeeRepository, times(1)).findById(2);
    }

    @Test
    public void deleteEmployeeTest() {

        doNothing().when(employeeRepository).deleteById(1);


        employeeService.delete(1);

      
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    public void getEmployeeDetailTest() {
   
        List<Employee> employees = List.of(employee);
        when(employeeRepository.getEmployeeDetail(1)).thenReturn(employees);


        List<Employee> result = employeeService.getEmployeeDetail(1);

   
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee.getId(), result.get(0).getId());
        verify(employeeRepository, times(1)).getEmployeeDetail(1);
    }

    @Test
    public void showAllEmployeesTest() {
       
        List<Employee> employees = List.of(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

       
        List<Employee> result = employeeService.showAllEmployees();

      
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee.getId(), result.get(0).getId());
        verify(employeeRepository, times(1)).findAll();
    }

}
