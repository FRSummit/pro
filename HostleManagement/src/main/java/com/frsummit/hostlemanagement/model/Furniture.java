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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Furniture implements Serializable {
    @Id
    private String id;
    private String type;
    @ManyToOne(cascade=CascadeType.ALL)
    private Room roomId;

    public Furniture() {
    }

    public Furniture(String id, String type, Room roomId) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Room getRoomId() {
        return roomId;
    }

    @Override
    public String toString() {
        return "Furniture{" + "id=" + id + ", type=" + type + ", roomId=" + roomId + '}';
    }
    
    public List<Furniture> getFurnitures() {
        List<Furniture> furnitureList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            furnitureList.addAll(session.createCriteria(Furniture.class).list());
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
