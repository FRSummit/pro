/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.airportreservation;

import com.frsummit.airportreservation.model.Address;
import com.frsummit.airportreservation.model.Booking;
import com.frsummit.airportreservation.Enums.CityEnum;
import com.frsummit.airportreservation.Enums.CountryEnum;
import com.frsummit.airportreservation.model.Customer;
import com.frsummit.airportreservation.DAO.CustomerDAO;
import com.frsummit.airportreservation.DAOImplementation.CustomerDAOImplementation;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author F R SUMMIT
 */
public class CustomerController implements Initializable {

    @FXML
    private ComboBox<CityEnum> cityBox;
    @FXML
    private ComboBox<CountryEnum> countryBox;
    @FXML
    private ListView<Customer> customerlistView;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerFirstNameField;
    @FXML
    private TextField houseNoField;
    @FXML
    private TextField customerLastNameField;
    @FXML
    private TextField streetNoField;
    @FXML
    private TextField provinceField;

    CustomerDAO customerDAO;
    private ObservableList<Customer> customerList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = FXCollections.observableArrayList();
        customerlistView.setItems(customerList);
        
        countryBox.getItems().addAll(CountryEnum.values());
        cityBox.getItems().addAll(CityEnum.values());
        
        customerDAO = new CustomerDAOImplementation();
        //List<Customer> customersList = new ArrayList<>();
        //Map<Integer, Customer> customerMap = new HashMap<>();
        
        //customerList = customerDAO.getCustomers();
        //customersList.forEach(customer -> customerMap.put(customer.getCustomerId(), customer));
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        int customerId = Integer.parseInt(customerIdField.getText());
        String firstName = customerFirstNameField.getText();
        String lastName = customerLastNameField.getText();
        int houseNo = Integer.parseInt(houseNoField.getText());
        int streetNo = Integer.parseInt(streetNoField.getText());
        CityEnum customerCity = cityBox.getSelectionModel().getSelectedItem();
        String province = provinceField.getText();
        CountryEnum customerCountry = countryBox.getSelectionModel().getSelectedItem();
        
        Customer customer = new Customer(customerId, firstName, lastName, customerCountry, new Address(houseNo, streetNo, customerCity, province));
        
        customerDAO.addCustomer(customer);
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
    }
    
}
