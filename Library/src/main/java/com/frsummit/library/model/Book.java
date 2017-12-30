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
public class Book {
    @Id
    private String id;
    private String name;
    @ManyToOne
    private Publisher pbId;
    @ManyToOne
    private Supplier suId;
    @ManyToOne
    private Catagory ctId;
    @ManyToOne
    private Author auId;

    public Book() {
    }

    public Book(String id, String name, Publisher pbId, Supplier suId, Catagory ctId, Author auId) {
        this.id = id;
        this.name = name;
        this.pbId = pbId;
        this.suId = suId;
        this.ctId = ctId;
        this.auId = auId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Publisher getPbId() {
        return pbId;
    }

    public Supplier getSuId() {
        return suId;
    }

    public Catagory getCtId() {
        return ctId;
    }

    public Author getAuId() {
        return auId;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", pbId=" + pbId + ", suId=" + suId + ", ctId=" + ctId + ", auId=" + auId + '}';
    }
    
    public List<Book> getBooks() {
        List<Book> bkList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            bkList.addAll(session.createCriteria(Book.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return bkList;
    }
    
}
