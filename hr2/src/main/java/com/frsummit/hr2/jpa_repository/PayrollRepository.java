package com.frsummit.hr2.jpa_repository;

import com.frsummit.hr2.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("payrollRepository")
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    //Payroll findByEmail(String payUserEmail);
}
