/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation.DAO;

import com.frsummit.airportreservation.model.Booking;
import java.util.List;

/**
 *
 * @author F R SUMMIT
 */
public interface BookingDAO {
    public List<Booking> getBookings();
    public Booking getBooking(int bookingNumber);
    public void addBooking(Booking booking);
    public void updateBooking(Booking booking);
    public void deleteBooking(Booking booking);
}
