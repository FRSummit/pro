/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Catagory {
    @Id
    private String id;
    private String subCat;

    public Catagory() {
    }

    public Catagory(String id, String subCat) {
        this.id = id;
        this.subCat = subCat;
    }

    public String getId() {
        return id;
    }

    public String getSubCat() {
        return subCat;
    }

    @Override
    public String toString() {
        return "Catagory{" + "id=" + id + ", subCat=" + subCat + '}';
    }
    
    public List<Catagory> getCataagories() {
        List<Catagory> catList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            catList.addAll(session.createCriteria(Catagory.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return catList;
    }
    
}
