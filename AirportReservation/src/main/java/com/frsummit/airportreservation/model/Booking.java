/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.model;

import com.frsummit.airportreservation.Enums.CityEnum;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "booking")
public class Booking implements Serializable {
    @Id
    @Column(name = "bk_no")
    private int bookingNumber;
    @Column(name = "bk_date", length = 15)
    @UpdateTimestamp
    private Date bookingDate;
    @Column(name = "balance", length = 8, precision = 2)
    private double balance;
    @Column(name = "t_price", length = 8, precision = 2)
    private double totalPrice;
    @Column(name = "paid_amt", length = 8, precision = 2)
    private double paidAmount;
    @Column(name = "f_price", length = 8, precision = 2)
    private double flightPrice;
    @Column(name = "b_class", length = 30)
    private String bookingClass;
//    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "f_no")
    private Flight flightNo;
    @UpdateTimestamp
    @Column(name = "dept_time")
    private Time departureTime;
    //@ManyToOne(cascade=CascadeType.ALL)
    @UpdateTimestamp
    @Column(name = "arr_time")
    private Time arrivalTime;
    //@ManyToOne(cascade=CascadeType.ALL)
    @Column(name = "dest", length = 30)
    private CityEnum airportDestination;
//    //@ManyToOne(cascade=CascadeType.ALL)
    @Column(name = "origin", length = 30)
    private CityEnum airportOrigin;
//    //@ManyToOne(cascade=CascadeType.ALL)
    @Column(name = "bk_city", length = 30)
    private CityEnum bookingCity;
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "c_id")
//    private Customer customerId;
//    //@ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "paid_by")
//    private Customer paidBy;

    public Booking() {
    }

    public Booking(int bookingNumber, double balance, double totalPrice, double paidAmount, double flightPrice, String bookingClass, Flight flightNo, CityEnum airportDestination, CityEnum airportOrigin, CityEnum bookingCity) {
        this.bookingNumber = bookingNumber;
        this.balance = balance;
        this.totalPrice = totalPrice;
        this.paidAmount = paidAmount;
        this.flightPrice = flightPrice;
        this.bookingClass=bookingClass;
        this.flightNo=flightNo;
        this.airportDestination = airportDestination;
        this.airportOrigin = airportOrigin;
        this.bookingCity = bookingCity;
    }
    
    
    public int getBookingNumber() {
        return bookingNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public String getBookingClass() {
        return bookingClass;
    }

//    public Flight getFlightNo() {
//        return flightNo;
//    }

    public CityEnum getAirportDestination() {
        return airportDestination;
    }

    public CityEnum getAirportOrigin() {
        return airportOrigin;
    }

    public CityEnum getBookingCity() {
        return bookingCity;
    }
//
//    public Customer getCustomerId() {
//        return customerId;
//    }
//
//    public Customer getPaidBy() {
//        return paidBy;
//    }
    
    @Override
    public String toString() {
        return "Booking{" + "bookingNumber=" + bookingNumber + ", bookingDate=" + bookingDate + '}';
    }

    public Flight getFlightNo() {
        return flightNo;
    }
    
}
