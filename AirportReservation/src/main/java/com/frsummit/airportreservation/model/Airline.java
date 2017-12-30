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
@Entity(name = "airline")
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_code", length = 10, nullable = false)
    private String airlineCode;
//    @Column(name = "airline_name", length = 20, nullable = false)
//    private String airlineName;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;

    public Airline() {
    }

    public Airline(String airlineCode) {
        this.airlineCode = airlineCode;
        //this.country = country;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

//    public Country getCountry() {
//        return country;
//    }
    
    @Override
    public String toString() {
        return "Airline{" + "airlineCode=" + airlineCode + '}';
    }
    
}
