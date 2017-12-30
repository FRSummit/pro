/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.Airport;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface AirportDAO {
    public List<Airport> getAirports();
    public Airport getAirport(int airportCode);
    public void addAirport(Airport airport);
    public void updateAirport(Airport airport);
    public void deleteAirport(Airport airport);
    
}
