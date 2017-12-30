/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAOImplementation;

import com.frsummit.airportreservation.DAO.FlightAvailableDAO;
import com.frsummit.airportreservation.model.FlightAvailable;
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
public class FlightAvailableDAOImplementation implements FlightAvailableDAO {
    List<FlightAvailable> flightAvailableList;
    Session session;
    Transaction transaction;

    @Override
    public List<FlightAvailable> getFlightAvailables() {
        flightAvailableList = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        try{
            flightAvailableList.addAll(session.createCriteria(FlightAvailable.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return flightAvailableList;
    }

    @Override
    public FlightAvailable getFlightAvailable(String flightNo) {
        return flightAvailableList.get(0);
    }
    
}
