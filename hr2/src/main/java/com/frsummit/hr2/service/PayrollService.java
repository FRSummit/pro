package com.frsummit.hr2.service;

import com.frsummit.hr2.model.Payroll;

import java.util.List;

public interface PayrollService {
    //public Payroll findPayrollUserByEmail(String payUserEmail);
    public void savePayroll(Payroll payroll);
    public List<Payroll> findAllPayrolls();
    public List<Payroll> findMyPayroll();
    public void updatePayroll(Payroll payroll);
}
