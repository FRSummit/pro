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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "fax")
public class Fax implements Serializable {
//    @Id
//    @Column(name = "fax_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int faxId;
    @Id
    @Column(name = "f_area_code", length = 3, nullable = false)
    private String faxAreaCode;
    @Id
    @Column(name = "f_country_code", length = 2, nullable = false)
    private String faxCountryCode;
    @Id
    @Column(name = "f_number", length=7, nullable = false)
    private String faxNumber;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Fax() {
    }

    public Fax(String faxAreaCode, String faxCountryCode, String faxNumber) {
        this.faxAreaCode = faxAreaCode;
        this.faxCountryCode = faxCountryCode;
        this.faxNumber = faxNumber;
    }

    public String getFaxAreaCode() {
        return faxAreaCode;
    }

    public String getFaxCountryCode() {
        return faxCountryCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    @Override
    public String toString() {
        return faxAreaCode + " " + faxCountryCode + " " + faxNumber;
    }
    
    
}
