package com.example.swipemicroservice.controller;

import com.example.swipemicroservice.dao.EmployeeRepository;
import com.example.swipemicroservice.dao.SwipeRepository;
import com.example.swipemicroservice.entity.AttendanceDetails;
import com.example.swipemicroservice.entity.Employee;
import com.example.swipemicroservice.entity.SwipeDetails;
import com.example.swipemicroservice.service.EmployeeService;
import com.example.swipemicroservice.service.SwipeService;
import com.example.swipemicroservice.util.AttendanceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class SwipeController {

   @Autowired
    EmployeeService empService;



    @Autowired
    SwipeService swipeService;

   /* List detailsLists=List.of(new SwipeDetails("1001","","12/7/2023","10:00:00"),
            new SwipeDetails("1001","","12/7/2023","10:45:00"),
            new SwipeDetails("1001","","12/7/2023","12:00:00"),
    new SwipeDetails("1001","","12/7/2023","16:00:00"),
    new SwipeDetails("1001","","12/7/2023","20:00:00"));*/



    @PostMapping("/employee/swipe")
    public ResponseEntity<SwipeDetails> Swipe(@RequestBody SwipeDetails details)
    {


        SwipeDetails dbDetails=null;
        try {

            SwipeDetails lastDetails=swipeService.getLastRecord();
            String id=null;
            if(lastDetails==null)
            {
                id="1";
            }
            else {
                id=lastDetails.getId();
                long temp=Long.parseLong(id);
                temp++;
                id=String.valueOf(temp);
            }

           /* ObjectMapper mapper=new ObjectMapper();
            =mapper.readValue(body,SwipeDetails.class);*/
            String pattern = "MM-dd-yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String dateTime[] = date.split(" ");
            //dateTime[0] + " time " + dateTime[1]


            details.setId(id);
            details.setDate(dateTime[0]);
            details.setTime(dateTime[1]);
            System.out.println(details.toString());
            dbDetails=swipeService.swipe(details);


        }
        catch(Exception e)
        {

            e.printStackTrace();
        }

            return ResponseEntity.status(HttpStatus.CREATED).body(dbDetails);
    }



    @GetMapping("/employee/attendance")
    public ResponseEntity<AttendanceDetails> getAttandance(@RequestBody SwipeDetails details) {


        AttendanceDetails attDetails = null;
        try {
            List<SwipeDetails> detailsLists = swipeService.findByUserIdAndDate(details.getUserId(), details.getDate());
            attDetails = AttendanceUtil.calculateAttendance(detailsLists);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(attDetails);

    }
    @PostMapping("/employee/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        Employee employeeDb = null;
        try {


            List<Employee> empList = empService.getLastRecord();
            Employee emp=empList.get(0);
            System.out.println("outtttttttttt");
            empList.forEach(e->System.out.println(e.toString()));

            if(emp==null)
            {
                employee.setId(101);
            }
            else {
                employee.setId(emp.getId()+1);
            }
            System.out.println("last Employee " + employee.toString());

           // employeeDb = empService.saveEmployee(employee);

            //System.out.println(employeeDb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDb);
    }

    @GetMapping("/employee/get")
    public ResponseEntity<List<Employee>> getEmployees() {

        List<Employee> employees=new ArrayList<>();
        try {
            empService.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


         return ResponseEntity.status(HttpStatus.OK).body(employees);

    }

}
