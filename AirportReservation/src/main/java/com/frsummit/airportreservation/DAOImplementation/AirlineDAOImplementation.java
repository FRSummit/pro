/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAOImplementation;

import com.frsummit.airportreservation.model.Airline;
import com.frsummit.airportreservation.DAO.AirlineDAO;
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
public class AirlineDAOImplementation implements AirlineDAO {
    List<Airline> airlinesList;
    Session session;
    Transaction transaction;

    @Override
    public List<Airline> getAirlines() {
        airlinesList = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        try{
            airlinesList.addAll(session.createCriteria(Airline.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return airlinesList;
    }

    @Override
    public Airline getAirline(int airlineCode) {
        return airlinesList.get(0);
    }

    @Override
    public void addAirline(Airline airline) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            session.save(airline);
            transaction.commit();
        }catch(HibernateException ex){
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        }finally{
            session.close();
        }
    }

    @Override
    public void updateAirline(Airline airline) {
    }

    @Override
    public void deleteAirline(Airline airline) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            session.delete(airline);
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
