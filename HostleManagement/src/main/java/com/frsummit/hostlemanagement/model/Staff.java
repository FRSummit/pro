/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hostlemanagement.model;

import com.frsummit.hostlemanagement.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Staff implements Serializable {
    @Id
    private String id;
    private String name;
    private String address;
    private String salary;
    private String phone;
    @ManyToOne(cascade=CascadeType.ALL)
    private Hall hallId;
    @ManyToOne
    private Mess mess;

    public Staff() {
    }

    public Staff(String id, String name, String address, String salary, String phone, Hall hallId, Mess mess) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.phone = phone;
        this.hallId = hallId;
        this.mess = mess;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSalary() {
        return salary;
    }

    public String getPhone() {
        return phone;
    }

    public Hall getHallId() {
        return hallId;
    }

    public Mess getMess() {
        return mess;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", name=" + name + ", address=" + address + ", salary=" + salary + ", phone=" + phone + ", hallId=" + hallId + '}';
    }
    
    public List<Staff> getStaffs() {
    List<Staff> staffList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            staffList.addAll(session.createCriteria(Staff.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return staffList;
    }
}
