/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAOImplementation;

import com.frsummit.airportreservation.model.Customer;
import com.frsummit.airportreservation.DAO.CustomerDAO;
import com.frsummit.airportreservation.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author F R SUMMIT
 */
public class CustomerDAOImplementation implements CustomerDAO {
    List<Customer> customersList;
    Session session;
    Transaction transaction;

    @Override
    public List<Customer> getCustomers() {
        customersList = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        try{
            customersList.addAll(session.createCriteria(Customer.class).list());
            transaction.commit();
        } catch (HibernateException ex) {
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            session.close();
        }
        return customersList;
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customersList.get(0);
    }

    @Override
    public void addCustomer(Customer customer) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            session.save(customer);
            transaction.commit();
        }catch(HibernateException ex){
            Logger.getLogger("con").log(Level.INFO, "Exception: {0}", ex.getMessage());
            ex.printStackTrace(System.err);
        }finally{
            session.close();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
    }

    @Override
    public void deleteCustomer(Customer customer) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            session.delete(customer);
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
