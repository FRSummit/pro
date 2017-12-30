/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "country")
public class Country implements Serializable {
    @Id
    @Column(name = "country_code", length = 2, nullable = false)
    private String countryCode;
    @Column(name = "country_name", length = 20, nullable = false)
    private String countryName;

    public Country() {
    }

    public Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return "Country{" + "countryCode=" + countryCode + ", countryName=" + countryName + '}';
    }
    
}
