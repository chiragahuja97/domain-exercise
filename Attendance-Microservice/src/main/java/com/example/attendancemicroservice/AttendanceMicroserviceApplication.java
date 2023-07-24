package com.example.attendancemicroservice;

import com.example.attendancemicroservice.service.AttendanceService;
import com.example.attendancemicroservice.util.KafkaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class AttendanceMicroserviceApplication {


    private KafkaUtil util;

    public static void main(String[] args) {
        SpringApplication.run(AttendanceMicroserviceApplication.class, args);

            System.out.println("get value");
        //KafkaUtil util=new KafkaUtil(new AttendanceService());

            //util.receiveEvent();


    }

    @Bean
    public KafkaUtil getKafkaUtil(AttendanceService service)
    {
        KafkaUtil util2=new KafkaUtil(service);
        util2.start();
        return util2;
    }


}
