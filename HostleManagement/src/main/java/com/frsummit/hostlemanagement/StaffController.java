/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hostlemanagement;

import com.frsummit.hostlemanagement.model.Hall;
import com.frsummit.hostlemanagement.model.Mess;
import com.frsummit.hostlemanagement.model.Staff;
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
public class StaffController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField hallIdField;
    @FXML
    private TextField messIdField;
    @FXML
    private ListView<Staff> listView;
    private ObservableList<Staff> stList;
    private List<Mess> messList = new ArrayList<>();
    private List<Hall> hallList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stList = FXCollections.observableArrayList();
        listView.setItems(stList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        stList.addAll(session.createCriteria(Staff.class).list());
        session.close();
        
        Mess m = new Mess();
        messList = m.getMesss();
        List<String> mNo = new ArrayList<>();
        messList.forEach((rr) -> {mNo.add(rr.getMesss() + "");});
        
        Hall h = new Hall();
        hallList = h.getHalls();
        List<String> hNo = new ArrayList<>();
        hallList.forEach((re) -> {mNo.add(re.getHalls() + "");});
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = idField.getText();
        String name = nameField.getText();
        String fn = addressField.getText();
        String dep = salaryField.getText();
        String phone = phoneField.getText();
        String age = hallIdField.getText();
        String room = messIdField.getText();
        
        Hall hl = null;
        for (Hall hh : hallList) {
            if (hh.getId() == age) {
                hl = hh;
            }
        }
        
        Mess ms = null;
        for (Mess mm : messList) {
            if (mm.getInCharge() == room) {
                ms = mm;
            }
        }
        
        Staff s = new Staff(id, name, fn, dep, phone, hl, ms);
        stList.add(s);
        
        session.save(s);
        tr.commit();
        session.close();
        
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        salaryField.setText("");
        phoneField.setText("");
        hallIdField.setText("");
        messIdField.setText("");
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
