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
public class Room implements Serializable {
    @Id
    private String id;
    private String capacity;
    @ManyToOne(cascade=CascadeType.ALL)
    private Hall hallId;

    public Room() {
    }

    public Room(String id, String capacity, Hall hallId) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", capacity=" + capacity +'}';
    }
    
    public List<Room> getRooms() {
        List<Room> roomList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            roomList.addAll(session.createCriteria(Room.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return roomList;
    }
}
