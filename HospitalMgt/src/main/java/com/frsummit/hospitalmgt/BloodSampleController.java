/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hospitalmgt;

import com.frsummit.hospitalmgt.model.BloodSample;
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
public class BloodSampleController implements Initializable {

    @FXML
    private TextField sampleField;
    @FXML
    private TextField groupField;
    @FXML
    private ListView<BloodSample> listView;
    private ObservableList<BloodSample> bsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bsList = FXCollections.observableArrayList();
        listView.setItems(bsList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        bsList.addAll(session.createCriteria(BloodSample.class).list());
        session.close();
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String sample = sampleField.getText();
        String group = groupField.getText();
        
        BloodSample br = new BloodSample(sample, group);
        bsList.add(br);
        
        session.save(br);
        tr.commit();
        session.close();
        
        sampleField.setText("");
        groupField.setText("");
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
