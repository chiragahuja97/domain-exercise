package com.example.attendancemicroservice.entity;

import jakarta.persistence.*;


@Entity(name="attendance_details")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"userId","date"})})
public class AttendanceDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String totalHours;
    private String date;

    private String userId;
    private String attendance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AttendanceDetails{" +
                "id='" + id + '\'' +
                ", totalHours='" + totalHours + '\'' +
                ", date='" + date + '\'' +
                ", user_id='" + userId + '\'' +
                ", attendance='" + attendance + '\'' +
                '}';
    }
}
