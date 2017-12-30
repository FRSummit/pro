package com.frsummit.hr2.serviceimpl;

import com.frsummit.hr2.jpa_repository.AttendanceRepository;
import com.frsummit.hr2.jpa_repository.UserRepository;
import com.frsummit.hr2.model.Attendance;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("attendanceService")
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUserAttendanceByPunch(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> findMyAllAttendance() {
        return entityManager.createQuery("SELECT a FROM Attendance AS a WHERE a.attendEmail='" + myEmail() + "'", Attendance.class).getResultList();
    }

    @Override
    public List<Attendance> findAllAttendance() {
        return entityManager.createQuery("SELECT a FROM Attendance AS a", Attendance.class).getResultList();
    }


    public String myEmail(){
        String myId;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByEmail(auth.getName());
        myId = u.getEmail().toString();
        return myId;
    }
}
