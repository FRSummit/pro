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
public class Mess implements Serializable {
    @Id
    private String inCharge;
    private String timing;

    public Mess() {
    }

    public Mess(String inCharge, String timing) {
        this.inCharge = inCharge;
        this.timing = timing;
    }

    public String getInCharge() {
        return inCharge;
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "Mess{" + "inCharge=" + inCharge + ", timing=" + timing + '}';
    }
    
    public List<Mess> getMesss() {
    List<Mess> messList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            messList.addAll(session.createCriteria(Mess.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return messList;
    }
}
