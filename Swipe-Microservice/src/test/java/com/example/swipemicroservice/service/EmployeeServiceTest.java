package com.example.swipemicroservice.service;

import com.example.swipemicroservice.dao.EmployeeRepository;
import com.example.swipemicroservice.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repo;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void getLastRecord() {
        Mockito.when(repo.getLastRecord()).thenReturn(new ArrayList<Employee>());

        List<Employee> emp=employeeService.getLastRecord();
        assertEquals(ArrayList.class,emp.getClass());

    }

    @Test
    void saveEmployee() {
        Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(new Employee());
        Employee emp=employeeService.saveEmployee(new Employee());
        assertEquals(Employee.class,emp.getClass());

    }

    @Test
    void findAll() {
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<Employee>());

        List<Employee> emp=employeeService.findAll();
        assertEquals(ArrayList.class,emp.getClass());
    }
}