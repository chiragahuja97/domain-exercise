package com.example.swipemicroservice.util;

import com.example.swipemicroservice.entity.AttendanceDetails;
import com.example.swipemicroservice.entity.SwipeDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class AttendanceUtil {

    public static AttendanceDetails calculateAttendance(List<SwipeDetails> detailsList) throws Exception {

        String attendance=null;
        double totalhours = 0;
        for (int i = 0; i < detailsList.size() - 2; i = i + 2) {

            String time1 = detailsList.get(i).getTime();
            String time2 = detailsList.get(i + 1).getTime();
            System.out.println("time1 " + time1 + " time2 " + time2);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            long difference = (date2.getTime() - date1.getTime());

            double diffHours = Long.valueOf(difference).doubleValue() / (60 * 60 * 1000) % 24;
            System.out.println("diference " + diffHours);
            totalhours += diffHours;
        }
        if (totalhours > 8.00) {
            System.out.println("Present ");
            attendance="Present";
        } else if (totalhours > 4.00 && totalhours < 8.00) {
            System.out.println("Half Day ");
            attendance="Half Day";
        } else {
            System.out.println("Absent ");
            attendance="Absent";
        }

        AttendanceDetails details=new AttendanceDetails();
        details.setUserId(String.valueOf(detailsList.get(0).getId()));
        details.setAttendance(attendance);
        details.setTotalHours(Double.toString(totalhours));
        details.setDate(detailsList.get(0).getDate());
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(details);
        sendEvent(json);
        return details;

    }

    public static void sendEvent(String details) {

        Properties properties = new Properties();

        // connect to Localhost
        properties.setProperty("bootstrap.servers", "127.0.0.1:29092");

        // set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


    // create a Producer Record
    ProducerRecord<String, String> producerRecord =
            new ProducerRecord<>("Mytopic", details);

    // send data
        producer.send(producerRecord);

    // tell the producer to send all data and block until done -- synchronous
        producer.flush();

    // flush and close the producer
        producer.close();
    }
}
