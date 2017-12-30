/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.frsummit.library.util.HibernateUtil;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Charge {
    @Id
    private String id;
    private String amount;
    private String type;
    @ManyToOne
    private Member memId;

    public Charge() {
    }

    public Charge(String id, String amount, String type, Member memId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.memId = memId;
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Member getMemId() {
        return memId;
    }

    @Override
    public String toString() {
        return "Charge{" + "id=" + id + ", amount=" + amount + ", type=" + type + ", memId=" + memId + '}';
    }
    
    public List<Charge> getHalls() {
        List<Charge> crgList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            crgList.addAll(session.createCriteria(Charge.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return crgList;
    }
    
}
