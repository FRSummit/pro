/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hostlemanagement;

import com.frsummit.hostlemanagement.model.Furniture;
import com.frsummit.hostlemanagement.model.Room;
import com.frsummit.hostlemanagement.util.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class FurnitureController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField roomIdField;
    @FXML
    private ListView<Furniture> llistView;
    private ObservableList<Furniture> furnitureList;
    private List<Room> roomList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        furnitureList = FXCollections.observableArrayList();
        llistView.setItems(furnitureList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        furnitureList.addAll(session.createCriteria(Furniture.class).list());
        session.close();
        
        Room rm = new Room();
        roomList = rm.getRooms();
        List<String> rNo = new ArrayList<>();
        roomList.forEach((rr) -> {rNo.add(rr.getRooms() + "");});
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = idField.getText();
        String type = typeField.getText();
        String room = roomIdField.getText();
        
        Room rom = null;
        for (Room r : roomList) {
            if (r.getId() == room) {
                rom = r;
            }
        }
        
        Furniture s = new Furniture(id, type, rom);
        furnitureList.add(s);
        
        session.save(s);
        tr.commit();
        session.close();
        
        idField.setText("");
        typeField.setText("");
        roomIdField.setText("");
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
