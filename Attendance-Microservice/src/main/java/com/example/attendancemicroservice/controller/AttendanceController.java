package com.example.attendancemicroservice.controller;
import com.example.attendancemicroservice.AttendanceMicroserviceApplication;
import com.example.attendancemicroservice.entity.AttendanceDetails;
import com.example.attendancemicroservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttendanceController {


    @Autowired
    private AttendanceService service;

    @PostMapping("/get/Attendance")
    public List<AttendanceDetails> getAttendance(@RequestBody AttendanceDetails details) {
        List<AttendanceDetails> detailsList=null;
        try {
            detailsList=service.getAttendance(details.getUserId(),details.getDate());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return detailsList;
    }
}
