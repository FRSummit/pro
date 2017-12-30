/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAOImplementation;

import com.frsummit.airportreservation.DAO.AirportDAO;
import com.frsummit.airportreservation.model.Airline;
import com.frsummit.airportreservation.model.Airport;
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
public class AirportDAOImplementation implements AirportDAO {
    List<Airport> airportsList;
    Session session;
    Transaction transaction;

    @Override
    public List<Airport> getAirports() {
        airportsList = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        try{
            airportsList.addAll(session.createCriteria(Airport.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return airportsList;
    }

    @Override
    public Airport getAirport(int airportCode) {
        return airportsList.get(0);
    }

    @Override
    public void addAirport(Airport airport) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            session.save(airport);
            transaction.commit();
        }catch(HibernateException ex){
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        }finally{
            session.close();
        }
    }

    @Override
    public void updateAirport(Airport airport) {
    }

    @Override
    public void deleteAirport(Airport airport) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            session.delete(airport);
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
