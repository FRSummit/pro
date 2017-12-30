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
import javax.persistence.OneToOne;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "airport")
public class Airport implements Serializable {
    @Id
    @Column(name = "airport_code", length = 3, nullable = false)
    private String airportCode;
    @Column(name = "airport_name", length = 50)
    private String airportName;
    @Column(name = "airport_tex", length = 6, precision = 2, nullable = false)
    private String airportTax;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    City city;

    public Airport() {
    }

    public Airport(String airportCode, String airportName, String airportTax, City city) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.airportTax = airportTax;
        this.city = city;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportTax() {
        return airportTax;
    }

    @Override
    public String toString() {
        return airportCode + " - " + airportName + " " + airportTax;
    }
    
}
