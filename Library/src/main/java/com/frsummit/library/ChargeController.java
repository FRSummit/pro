/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.library;

import com.frsummit.library.model.Charge;
import com.frsummit.library.model.Member;
import com.frsummit.library.util.HibernateUtil;
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
public class ChargeController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField type;
    @FXML
    private TextField memId;
    @FXML
    private ListView<Charge> listView;
    private ObservableList<Charge> stList;
    private List<Member> roomList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stList = FXCollections.observableArrayList();
        listView.setItems(stList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        stList.addAll(session.createCriteria(Charge.class).list());
        session.close();
        
        Member rm = new Member();
        roomList = rm.getMembers();
        List<String> rNo = new ArrayList<>();
        roomList.forEach((rr) -> {rNo.add(rr.getId()+ "");});
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = idField.getText();
        String name = amountField.getText();
        String fn = type.getText();
        String dep = memId.getText();
        
        Member rom = null;
        for (Member r : roomList) {
            if (r.getId() == dep) {
                rom = r;
            }
        }
        
        Charge s = new Charge(id, name, fn, rom);
        stList.add(s);
        
        session.save(s);
        tr.commit();
        session.close();
        
        idField.setText("");
        amountField.setText("");
        type.setText("");
        memId.setText("");
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
