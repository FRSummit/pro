package com.frsummit.airportreservation;

import com.frsummit.airportreservation.DAO.BookingDAO;
import com.frsummit.airportreservation.DAOImplementation.BookingDAOImplemetation;
import com.frsummit.airportreservation.DAO.CustomerDAO;
import com.frsummit.airportreservation.DAOImplementation.CustomerDAOImplementation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CustomerDAO customerDAO = new CustomerDAOImplementation();
        BookingDAO bookingDAO = new BookingDAOImplemetation();
        //List<Customer> customersList = new ArrayList<>();
        //Map<Integer, Customer> customerMap = new HashMap<>();
        
        //customersList = customerDAO.getCustomers();
        //Customer c = customerDAO.getCustomer(0);
        //customersList.forEach(customer -> customerMap.put(customer.getCustomerId(), customer));
        //System.out.println(customersList);

        //Customer customer = new Customer(17, "Nasrin", "Akter", "BD", new Address(1, 1, "Kafrul", "Dhaka"));
        //customerDAO.addCustomer(customer);
        //Booking b = new Booking(132, 12, 12, 34, 67, "Business", "UK", "US", "NY");
        //bookingDAO.addBooking(b);
        
        //Customer customer = new Customer(123);
        //customerDAO.deleteCustomer(customer);
//        CountryDAO countryDAO = new CountryDAOImplementation();
//        AirlineDAO airlineDAO = new AirlineDAOImplementation();
//        Country c = new Country("PR", "Paris");
//        //Airline a = new Airline("AK234");
//        countryDAO.addCountry(c);
//        //airlineDAO.addAirline(a);
        
    }    

    @FXML
    private void handleBookNowAction(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Booking.fxml"));
            Region contentRootRegion = (Region) loader.load();
            Scene scene = new Scene(contentRootRegion);
            MainApp.getStage().setScene(scene);
            MainApp.getStage().show();

//            MainApp.getStage().setOnCloseRequest((event) -> {
//                HibernateUtil.getSessionFactory().close();
//            });
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.setTitle("Internal Error");
            alert.setHeaderText("Internal Error Occcured");
            alert.setContentText("Can not load Scene . Please contact software administration ");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
    }

    @FXML
    private void handleAvailableFlightAction(ActionEvent event) {
    }

    @FXML
    private void handlePaymentAction(ActionEvent event) {
    }

    @FXML
    private void customerDetailsAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Customer.fxml"));
            Region contentRootRegion = (Region) loader.load();
            Scene scene = new Scene(contentRootRegion);
            MainApp.getStage().setScene(scene);
            MainApp.getStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.setTitle("Internal Error");
            alert.setHeaderText("Internal Error Occcured");
            alert.setContentText("Can not load Scene . Please contact software administration ");
            alert.showAndWait();
        }
    }

    @FXML
    private void flightInformationAction(ActionEvent event) {
    }

    @FXML
    private void tickerReservationAction(ActionEvent event) {
    }

    @FXML
    private void enquaryAction(ActionEvent event) {
    }

    @FXML
    private void cancelFlightAction(ActionEvent event) {
    }
}
