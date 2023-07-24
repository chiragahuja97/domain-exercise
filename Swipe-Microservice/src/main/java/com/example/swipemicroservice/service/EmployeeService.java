package com.example.swipemicroservice.service;

import com.example.swipemicroservice.dao.EmployeeRepository;
import com.example.swipemicroservice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repo;

    public List<Employee> getLastRecord()
    {
        return repo.getLastRecord();
    }

    public Employee saveEmployee(Employee emp)
    {
        return repo.save(emp);
    }

    public List<Employee> findAll()
    {
        return repo.findAll();
    }
}
