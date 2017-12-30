/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.model;

import com.frsummit.airportreservation.Enums.CityEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author F R SUMMIT
 */
@Embeddable
public class Address implements Serializable {
    @Column(name = "house_no", nullable = false)
    private int houseNumber;
    @Column(name = "street_no", nullable = false)
    private int streetNumber;
    @Column(name = "city", length=20, nullable = false)
    private CityEnum city;
    @Column(name = "province", length=20, nullable = false)
    private String province;

    public Address() {
    }

    public Address(int houseNumber, int streetNumber, CityEnum city, String province) {
        this.houseNumber = houseNumber;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public CityEnum getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    @Override
    public String toString() {
        return "Address{" + "houseNumber=" + houseNumber + ", streetNumber=" + streetNumber + ", city=" + city + ", province=" + province + '}';
    }
    
    
}
