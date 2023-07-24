package com.example.swipemicroservice.dao;

import com.example.swipemicroservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EmployeeRepository extends MongoRepository<Employee,Long> {

    @Query(value = "db.employee_details.find({}).sort({_id:-1}).limit(1)")
    public List<Employee> getLastRecord();
}
