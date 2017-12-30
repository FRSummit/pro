/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.Customer;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface CustomerDAO {
    public List<Customer> getCustomers();
    public Customer getCustomer(int customerId);
    public void addCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
}
