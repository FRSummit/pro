/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.Airline;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface AirlineDAO {
    public List<Airline> getAirlines();
    public Airline getAirline(int airlineCode);
    public void addAirline(Airline airline);
    public void updateAirline(Airline airline);
    public void deleteAirline(Airline airline);
}
