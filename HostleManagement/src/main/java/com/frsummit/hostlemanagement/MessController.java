/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hostlemanagement;

import com.frsummit.hostlemanagement.model.Mess;
import com.frsummit.hostlemanagement.util.HibernateUtil;
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
public class MessController implements Initializable {

    @FXML
    private TextField chargeField;
    @FXML
    private TextField timingField;
    @FXML
    private ListView<Mess> listView;
    private ObservableList<Mess> stList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stList = FXCollections.observableArrayList();
        listView.setItems(stList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        stList.addAll(session.createCriteria(Mess.class).list());
        session.close();
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = chargeField.getText();
        String name = timingField.getText();
        
        Mess s = new Mess(id, name);
        stList.add(s);
        
        session.save(s);
        tr.commit();
        session.close();
        
        chargeField.setText("");
        timingField.setText("");
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
