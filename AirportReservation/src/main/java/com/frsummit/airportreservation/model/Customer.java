/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.model;

import com.frsummit.airportreservation.Enums.CountryEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "customer")
public class Customer implements Serializable {
    @Id
    @Column(name ="id", nullable = false)
    private int customerId;
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;
    @Column(name = "country", length = 20, nullable = false)
    private CountryEnum country;
    @Embedded
    private Address address;

    public Customer() {
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    public Customer(int customerId, String firstName, String lastName, CountryEnum country, Address address) {
        this();
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CountryEnum getCountry() {
        return country;
    }

    @Override
    public String toString() {
        //return "Customer{" + "customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", address=" + address + '}';
        return customerId + "-" + firstName + " " + lastName;
    }
    
}
