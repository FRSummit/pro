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
import javax.persistence.OneToMany;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Student implements Serializable {
    @Id
    private String id;
    private String name;
    private String fatherName;
    private String department;
    private String phone;
    private String age;
    @ManyToOne(cascade=CascadeType.ALL)
    private Room room;

    public Student() {
    }

    public Student(String id, String name, String fatherName, String department, String phone, String age, Room room) {
        this.id = id;
        this.name = name;
        this.fatherName = fatherName;
        this.department = department;
        this.phone = phone;
        this.age = age;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", fatherName=" + fatherName + ", department=" + department + ", phone=" + phone + ", age=" + age + '}';
    }

    public Room getRoom() {
        return room;
    }
    
    
    public List<Student> getStudents() {
        List<Student> stList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            stList.addAll(session.createCriteria(Student.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return stList;
    }
}
