/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.FlightAvailable;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface FlightAvailableDAO {
    public List<FlightAvailable> getFlightAvailables();
    public FlightAvailable getFlightAvailable(String flightNo);
    
}
