package com.frsummit.hr2.service;

import com.frsummit.hr2.model.Attendance;

import java.util.List;

public interface AttendanceService {

    public void saveUserAttendanceByPunch(Attendance attendance);
    public List<Attendance> findMyAllAttendance();
    public List<Attendance> findAllAttendance();
}
