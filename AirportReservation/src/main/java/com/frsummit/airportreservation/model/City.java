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
@Entity(name = "city")
public class City implements Serializable {
    @Id
    @Column(name = "city_id", nullable = false)
    private int cityId;
    @Column(name = "city_name", length = 20)
    private String cityName;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "country_code", nullable = false)
    Country country;

    public City() {
    }

    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return cityId + " " + cityName;
    }
    
}
