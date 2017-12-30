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
import javax.persistence.OneToMany;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Hall implements Serializable {
    @Id
    private String id;
    private String numberOfRooms;
    private String numberOfStudents;
    private String address;

    public Hall() {
    }

    public Hall(String id, String numberOfRooms, String numberOfStudents, String address) {
        this.id = id;
        this.numberOfRooms = numberOfRooms;
        this.numberOfStudents = numberOfStudents;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Hall{" + "id=" + id + ", numberOfRooms=" + numberOfRooms + ", numberOfStudents=" + numberOfStudents + ", address=" + address + '}';
    }
    
    public List<Hall> getHalls() {
        List<Hall> hallList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            hallList.addAll(session.createCriteria(Hall.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return hallList;
    }
}
