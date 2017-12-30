/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAOImplementation;

import com.frsummit.airportreservation.model.Airline;
import com.frsummit.airportreservation.model.Booking;
import com.frsummit.airportreservation.DAO.BookingDAO;
import com.frsummit.airportreservation.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author F R SUMMIT
 */
public class BookingDAOImplemetation implements BookingDAO {
    List<Booking> bookingList;
    Session session;
    Transaction transaction;

    @Override
    public List<Booking> getBookings() {
        bookingList = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        try{
            bookingList.addAll(session.createCriteria(Booking.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return bookingList;
    }

    @Override
    public Booking getBooking(int bookingNumber) {
        return bookingList.get(0);
    }

    @Override
    public void addBooking(Booking booking) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            session.save(booking);
            transaction.commit();
        }catch(HibernateException ex){
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        }finally{
            session.close();
        }
    }

    @Override
    public void updateBooking(Booking booking) {
    }

    @Override
    public void deleteBooking(Booking booking) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            session.delete(booking);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
    }
    
}
