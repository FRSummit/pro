/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.Country;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface CountryDAO {
    public List<Country> getCountrys();
    public Country getCountry(int countryCode);
    public void addCountry(Country country);
    public void updateCountry(Country country);
    public void deleteCountry(Country country);
}
