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
import javax.persistence.ManyToOne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Fee implements Serializable {
    @Id
    private String fee;
    private String feeStatus;
    @ManyToOne(cascade=CascadeType.ALL)
    private Student studentId;

    public Fee() {
    }

    public Fee(String fee, String feeStatus, Student studentId) {
        this.fee = fee;
        this.feeStatus = feeStatus;
        this.studentId = studentId;
    }

    public String getFee() {
        return fee;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public Student getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Fee{" + "fee=" + fee + ", feeStatus=" + feeStatus + ", studentId=" + studentId + '}';
    }
    
    public List<Fee> getFurnitures() {
        List<Fee> furnitureList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            furnitureList.addAll(session.createCriteria(Fee.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return furnitureList;
    }
    
}
