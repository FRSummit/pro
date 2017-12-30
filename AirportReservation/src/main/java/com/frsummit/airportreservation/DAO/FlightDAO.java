/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.Flight;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface FlightDAO {
    public List<Flight> getFlights();
    public Flight getFlight(int flightNumber);
    public void addFlight(Flight flight);
    public void updateFlight(Flight flight);
    public void deleteFlight(Flight flight);
    
}
