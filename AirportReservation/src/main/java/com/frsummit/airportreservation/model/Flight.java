/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "flight")
public class Flight implements Serializable {
    @Id
    @Column(name = "flight_no", length = 10, nullable = false)
    private String flightNumber;
    @Column(name = "smoking_allow", length = 4)
    private String smokingAllow;
    @Column(name = "b_class_avl", length = 4)
    private String businessClassAvailable;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "airline_code")
    Airline airline;

    public Flight() {
    }

    public Flight(String flightNumber, String smokingAllow, String businessClassAvailable) {
        this.flightNumber = flightNumber;
        this.smokingAllow = smokingAllow;
        this.businessClassAvailable = businessClassAvailable;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSmokingAllow() {
        return smokingAllow;
    }

    public String getBusinessClassAvailable() {
        return businessClassAvailable;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flightNumber + ", smokingAllow=" + smokingAllow + ", businessClassAvailable=" + businessClassAvailable + '}';
    }
    
}
