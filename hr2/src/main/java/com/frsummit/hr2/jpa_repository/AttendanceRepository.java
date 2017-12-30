package com.frsummit.hr2.jpa_repository;

import com.frsummit.hr2.model.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("attendanceRepository")
public interface AttendanceRepository extends CrudRepository<Attendance, String> {
}
