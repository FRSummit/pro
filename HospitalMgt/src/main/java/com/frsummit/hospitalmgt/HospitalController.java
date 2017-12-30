/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hospitalmgt;

import com.frsummit.hospitalmgt.model.Hospitals;
import com.frsummit.hospitalmgt.util.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author F R SUMMIT
 */
public class HospitalController implements Initializable {

    @FXML
    private ListView<Hospitals> listView;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField groupField;
    @FXML
    private TextField quantityField;
    private ObservableList<Hospitals> hosList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hosList = FXCollections.observableArrayList();
        listView.setItems(hosList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        hosList.addAll(session.createCriteria(Hospitals.class).list());
        session.close();
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = idField.getText();
        String name = nameField.getText();
        String group = groupField.getText();
        String quantity = quantityField.getText();
        
        Hospitals br = new Hospitals(id, name, group, quantity);
        hosList.add(br);
        
        session.save(br);
        tr.commit();
        session.close();
        
        idField.setText("");
        nameField.setText("");
        groupField.setText("");
        quantityField.setText("");
    }

    @FXML
    private void home(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Scene.fxml"));
            Region contentRootRegion = (Region) loader.load();
            Scene scene = new Scene(contentRootRegion);
            MainApp.getStage().setScene(scene);
            MainApp.getStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
