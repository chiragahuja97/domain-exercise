package com.example.attendancemicroservice.service;



import com.example.attendancemicroservice.dao.AttendanceDao;
import com.example.attendancemicroservice.entity.AttendanceDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {



    @Autowired
    AttendanceDao dao;

   /* public AttendanceService(AttendanceDao dao) {
        this.dao = dao;
    }*/
    public AttendanceDetails saveAttendanceDetails(AttendanceDetails details)
    {
        return dao.save(details);
    }

    public List<AttendanceDetails> getAttendance(String userId, String date)
    {

        return dao.findByUserIdAndDate(userId,date);
    }
}
