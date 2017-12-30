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
import javax.persistence.OneToOne;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "flight_available")
public class FlightAvailable implements Serializable {
    @Column(name = "f_length", length = 30, nullable = false)
    private String flightLength;
    @Id
    @Column(name = "dept_time", length = 30, nullable = false)
    private String departureTime;
    @Id
    @Column(name = "arr_time", length = 30, nullable = false)
    private String arrivalTime;
    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "flight_no", nullable = false)
    private Flight flightNo;
    @Column(name = "b_bus_seat", nullable = false)
    private int bookedBusinessSeat;
    @Column(name = "b_eco_seat", nullable = false)
    private int bookedEconomySeat;
    @Column(name = "t_bus_seat", nullable = false)
    private int totalBusinessSeat;
    @Column(name = "t_eco_seat", nullable = false)
    private int totalEconomySeat;
    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "origin", nullable = false)
    private Airport airportOrigin;
    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "dest", nullable = false)
    private Airport airportDestination;

    public FlightAvailable() {
    }

    public FlightAvailable(String flightLength, String departureTime, String arrivalTime, Flight flightNo, int bookedBusinessSeat, int bookedEconomySeat, int totalBusinessSeat, int totalEconomySeat, Airport airportOrigin, Airport airportDestination) {
        this.flightLength = flightLength;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightNo = flightNo;
        this.bookedBusinessSeat = bookedBusinessSeat;
        this.bookedEconomySeat = bookedEconomySeat;
        this.totalBusinessSeat = totalBusinessSeat;
        this.totalEconomySeat = totalEconomySeat;
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
    }

    public String getFlightLength() {
        return flightLength;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Flight getFlightNo() {
        return flightNo;
    }

    public int getBookedBusinessSeat() {
        return bookedBusinessSeat;
    }

    public int getBookedEconomySeat() {
        return bookedEconomySeat;
    }

    public int getTotalBusinessSeat() {
        return totalBusinessSeat;
    }

    public int getTotalEconomySeat() {
        return totalEconomySeat;
    }

    public Airport getAirportOrigin() {
        return airportOrigin;
    }

    public Airport getAirportDestination() {
        return airportDestination;
    }

    @Override
    public String toString() {
        return "FlightAvailable{" + "flightLength=" + flightLength + ", flight=" + flightNo + ", airportOrigin=" + airportOrigin + ", airportDestination=" + airportDestination + '}';
    }
    
}
