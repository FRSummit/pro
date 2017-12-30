/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAOImplementation;

import com.frsummit.airportreservation.model.Airline;
import com.frsummit.airportreservation.model.Country;
import com.frsummit.airportreservation.DAO.CountryDAO;
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
public class CountryDAOImplementation implements CountryDAO {
    List<Country> countriesList;
    Session session;
    Transaction transaction;

    @Override
    public List<Country> getCountrys() {
        countriesList = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        try{
            countriesList.addAll(session.createCriteria(Country.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return countriesList;
    }

    @Override
    public Country getCountry(int countryCode) {
        return countriesList.get(0);
    }

    @Override
    public void addCountry(Country country) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            session.save(country);
            transaction.commit();
        }catch(HibernateException ex){
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        }finally{
            session.close();
        }
    }

    @Override
    public void updateCountry(Country country) {
    }

    @Override
    public void deleteCountry(Country country) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            session.delete(country);
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
