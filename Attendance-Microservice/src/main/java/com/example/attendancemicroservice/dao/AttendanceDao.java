package com.example.attendancemicroservice.dao;


import com.example.attendancemicroservice.entity.AttendanceDetails;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceDao extends JpaRepository<AttendanceDetails,String> {


    public List<AttendanceDetails> findByUserIdAndDate(String userId,String date);
}
