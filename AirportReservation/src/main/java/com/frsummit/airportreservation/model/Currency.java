/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author F R SUMMIT
 */
@Entity(name = "currency")
public class Currency implements Serializable {
    @Id
    @Column(name = "from_currency", length = 3, nullable = false)
    private String fromCurrency;
    @Id
    @Column(name = "to_currency", length = 3, nullable = false)
    private String toCurrency;
    @Column(name = "exchange_rate", length = 8, precision = 4, nullable = false)
    private double exchangeRate;

    public Currency() {
    }

    public Currency(String fromCurrency, String toCurrency, double exchangeRate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String toString() {
        return fromCurrency + " -> " + toCurrency + " = " + exchangeRate;
    }
    
}
