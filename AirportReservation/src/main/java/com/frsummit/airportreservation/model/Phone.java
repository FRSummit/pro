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
@Entity(name = "phone")
public class Phone implements Serializable {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int phoneId;
    @Id
    @Column(name = "phn_Country_code", length = 2, nullable = false)
    private String phoneCountryCode;
    @Id
    @Column(name = "phn_area_code", length = 3, nullable = false)
    private String phoneAreaCode;
    @Id
    @Column(name = "phone_number", length = 7, nullable = false)
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Phone() {
    }

    public Phone(String phoneCountryCode, String phoneAreaCode, String phoneNumber) {
        this.phoneCountryCode = phoneCountryCode;
        this.phoneAreaCode = phoneAreaCode;
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return phoneCountryCode;
    }

    public String getAreaCode() {
        return phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return phoneCountryCode + " " + phoneAreaCode + " " + phoneNumber + '}';
    }
}
