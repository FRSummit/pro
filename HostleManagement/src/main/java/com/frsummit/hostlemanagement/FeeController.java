/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hostlemanagement;

import com.frsummit.hostlemanagement.model.Fee;
import com.frsummit.hostlemanagement.model.Student;
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
public class FeeController implements Initializable {

    @FXML
    private TextField feeField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField stdIdField;
    @FXML
    private ListView<Fee> listView;
    private ObservableList<Fee> feeList;
    private List<Student> sList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        feeList = FXCollections.observableArrayList();
        listView.setItems(feeList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        feeList.addAll(session.createCriteria(Fee.class).list());
        session.close();
        
        Student s = new Student();
        sList = s.getStudents();
        List<String> sId = new ArrayList<>();
        sList.forEach((ss) -> {sId.add(ss.getStudents() + "");});
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String fee = feeField.getText();
        String feeStatus = statusField.getText();
        String stid = stdIdField.getText();
        
        Student sss = null;
        for (Student stt: sList) {
            if (stt.getId() == stid) {
                sss = stt;
            }
        }
        
        Fee ff = new Fee(fee, feeStatus, sss);
        feeList.add(ff);
        
        session.save(ff);
        tr.commit();
        session.close();
        
        feeField.setText("");
        statusField.setText("");
        stdIdField.setText("");
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
