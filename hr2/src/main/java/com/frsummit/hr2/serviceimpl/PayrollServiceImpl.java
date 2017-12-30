package com.frsummit.hr2.serviceimpl;

import com.frsummit.hr2.jpa_repository.PayrollRepository;
import com.frsummit.hr2.jpa_repository.UserRepository;
import com.frsummit.hr2.model.Payroll;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("payrollService")
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public Payroll findPayrollUserByEmail(String payUserEmail) {
//        return payrollRepository.findByEmail(payUserEmail);
//    }


    @Override
    public void savePayroll(Payroll payroll) {
        payrollRepository.save(payroll);
    }

    @Override
    public List<Payroll> findAllPayrolls() {
        return entityManager.createQuery("SELECT p FROM Payroll AS p", Payroll.class).getResultList();
    }

    @Override
    public List<Payroll> findMyPayroll() {
        return entityManager.createQuery("SELECT p FROM Payroll AS p WHERE p.payUserEmail = '" + myId() + "'", Payroll.class).getResultList();
    }

    @Override
    public void updatePayroll(Payroll payroll) {
    }



    public String myId(){
        String myId;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByEmail(auth.getName());
        myId = u.getEmail().toString();
        return myId;
    }
}
