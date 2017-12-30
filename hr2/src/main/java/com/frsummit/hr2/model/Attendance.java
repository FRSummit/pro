package com.frsummit.hr2.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "att_id")
    private int attendId;

    @Column(name = "email")
    private String attendEmail;

    @UpdateTimestamp
    @Column(name = "punch_time")
    private Date punchTime;

    @Column(name = "in_out_status")
    private String inOutStatus;

    public Attendance() {
    }

    public Attendance(String attendEmail) {
        this.attendEmail = attendEmail;
        this.inOutStatus = "IN";
    }

    public int getAttendId() {
        return attendId;
    }

    public void setAttendId(int attendId) {
        this.attendId = attendId;
    }

    public String getAttendEmail() {
        return attendEmail;
    }

    public void setAttendEmail(String attendEmail) {
        this.attendEmail = attendEmail;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public String getInOutStatus() {
        return inOutStatus;
    }

    public void setInOutStatus(String inOutStatus) {
        this.inOutStatus = inOutStatus;
    }
}
