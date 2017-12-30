/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation;

import com.frsummit.airportreservation.model.Address;
import com.frsummit.airportreservation.model.Booking;
import com.frsummit.airportreservation.DAO.BookingDAO;
import com.frsummit.airportreservation.DAOImplementation.BookingDAOImplemetation;
import com.frsummit.airportreservation.Enums.CityEnum;
import com.frsummit.airportreservation.Enums.CountryEnum;
import com.frsummit.airportreservation.model.Customer;
import com.frsummit.airportreservation.DAO.CustomerDAO;
import com.frsummit.airportreservation.DAOImplementation.CustomerDAOImplementation;
import com.frsummit.airportreservation.model.Flight;
import com.frsummit.airportreservation.DAO.FlightDAO;
import com.frsummit.airportreservation.DAOImplementation.FlightDAOImplementation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author F R SUMMIT
 */
public class BookingController implements Initializable {

    @FXML
    private TextField bookingNoField;
    @FXML
    private TextField balanceField;
    @FXML
    private TextField flightPriceField;
    @FXML
    private TextField bookingClassField;
    @FXML
    private TextField paidAmountField;
    @FXML
    private TextField totalPriceField;
    @FXML
    private TextField flightNoField;
    @FXML
    private ComboBox<CityEnum> originBox;
    @FXML
    private ComboBox<CityEnum> destinationBox;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField cfNameField;
    @FXML
    private TextField clNameField;
    @FXML
    private TextField houseNoField;
    @FXML
    private TextField streetNoField;
    @FXML
    private TextField privinceField;
    @FXML
    private ComboBox<CityEnum> cityBox;
    @FXML
    private ComboBox<CountryEnum> countryBox;
    @FXML
    private ComboBox<CityEnum> bookingCityBox;
    
    CustomerDAO customerDAO;
    BookingDAO bookingDAO;
    private List<Flight> flightList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        originBox.getItems().addAll(CityEnum.values());
        destinationBox.getItems().addAll(CityEnum.values());
        cityBox.getItems().addAll(CityEnum.values());
        countryBox.getItems().addAll(CountryEnum.values());
        bookingCityBox.getItems().addAll(CityEnum.values());
        
        customerDAO = new CustomerDAOImplementation();
        bookingDAO = new BookingDAOImplemetation();
        FlightDAO flightDAO = new FlightDAOImplementation();
        flightList = flightDAO.getFlights();
        List<String> flightNumberList = new ArrayList<>();
        flightList.forEach((student) -> {
            flightNumberList.add(student.getFlightNumber() + "");
        });
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        int bookingNumber = Integer.parseInt(bookingNoField.getText());
        String flightNumber = flightNoField.getText();
        String bookingClass = bookingClassField.getText();
        CityEnum bookingCity = bookingCityBox.getSelectionModel().getSelectedItem();
        double balance = Double.parseDouble(balanceField.getText());
        double totalPrice = Double.parseDouble(totalPriceField.getText());
        double flightPrice = Double.parseDouble(flightPriceField.getText());
        double paidAmount = Double.parseDouble(paidAmountField.getText());
        CityEnum origin = originBox.getSelectionModel().getSelectedItem();
        CityEnum destination = originBox.getSelectionModel().getSelectedItem();
        
        Flight flightNo = null;
        for (Flight flNo : flightList) {
            if (flNo.getFlightNumber() == flightNumber) {
                flightNo = flNo;
            }
        }
        
        
        int customerId = Integer.parseInt(customerIdField.getText());
        String firstName = cfNameField.getText();
        String lastName = clNameField.getText();
        int houseNo = Integer.parseInt(houseNoField.getText());
        int streetNo = Integer.parseInt(streetNoField.getText());
        CityEnum customerCity = cityBox.getSelectionModel().getSelectedItem();
        String province = privinceField.getText();
        CountryEnum customerCountry = countryBox.getSelectionModel().getSelectedItem();
        
        Booking booking = new Booking(bookingNumber, balance, totalPrice, paidAmount, flightPrice, bookingClass, flightNo, destination, origin, bookingCity); 
        Customer customer = new Customer(customerId, firstName, lastName, customerCountry, new Address(houseNo, streetNo, customerCity, province));
        
        bookingDAO.addBooking(booking);
        customerDAO.addCustomer(customer);
    }


    @FXML
    private void handleSearchAction(ActionEvent event) {
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        int bookingNumber = Integer.parseInt(bookingNoField.getText());
        String flightNumber = flightNoField.getText();
        String bookingClass = bookingClassField.getText();
        CityEnum bookingCity = bookingCityBox.getSelectionModel().getSelectedItem();
        double balance = Double.parseDouble(balanceField.getText());
        double totalPrice = Double.parseDouble(totalPriceField.getText());
        double flightPrice = Double.parseDouble(flightPriceField.getText());
        double paidAmount = Double.parseDouble(paidAmountField.getText());
        CityEnum origin = originBox.getSelectionModel().getSelectedItem();
        CityEnum destination = originBox.getSelectionModel().getSelectedItem();
        CountryEnum customerCountry = countryBox.getSelectionModel().getSelectedItem();
        
        Flight flightNo = null;
        for (Flight flNo : flightList) {
            if (flNo.getFlightNumber() == flightNumber) {
                flightNo = flNo;
            }
        }
        
        Booking booking = new Booking(bookingNumber, balance, totalPrice, paidAmount, flightPrice, bookingClass, flightNo, destination, origin, bookingCity); 
        
        bookingDAO.deleteBooking(booking);
    }
    
}
