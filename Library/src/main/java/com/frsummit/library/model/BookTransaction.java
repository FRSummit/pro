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
public class BookTransaction {
    @Id
    private String id;
    @ManyToOne
    private Member memId;
    @ManyToOne
    private Book bookId;

    public BookTransaction() {
    }

    public BookTransaction(String id, Member memId, Book bookId) {
        this.id = id;
        this.memId = memId;
        this.bookId = bookId;
    }

    public String getId() {
        return id;
    }

    public Member getMemId() {
        return memId;
    }

    public Book getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "BookTransaction{" + "id=" + id + ", memId=" + memId + ", bookId=" + bookId + '}';
    }
    
    public List<BookTransaction> getBookTrs() {
        List<BookTransaction> btrList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        try{
            btrList.addAll(session.createCriteria(BookTransaction.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return btrList;
    }
    
}
