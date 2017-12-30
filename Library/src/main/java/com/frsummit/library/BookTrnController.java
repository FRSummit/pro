/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.library;

import com.frsummit.library.model.Book;
import com.frsummit.library.model.BookTransaction;
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
public class BookTrnController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField memIdField;
    @FXML
    private TextField bookIdField;
    @FXML
    private ListView<BookTransaction> listView;
    private ObservableList<BookTransaction> stList;
    private List<Book> roomList = new ArrayList<>();
    private List<Member> memList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stList = FXCollections.observableArrayList();
        listView.setItems(stList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        stList.addAll(session.createCriteria(BookTransaction.class).list());
        session.close();
        
        Book rm = new Book();
        roomList = rm.getBooks();
        List<String> rNo = new ArrayList<>();
        roomList.forEach((rr) -> {rNo.add(rr.getId() + "");});
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = idField.getText();
        String name = memIdField.getText();
        String fn = bookIdField.getText();
        
        Member ro = null;
        for (Member m : memList) {
            if (m.getId() == name) {
                ro = m;
            }
        }
        
        Book rom = null;
        for (Book r : roomList) {
            if (r.getId() == fn) {
                rom = r;
            }
        }
        
        BookTransaction s = new BookTransaction(id, ro, rom);
        stList.add(s);
        
        session.save(s);
        tr.commit();
        session.close();
        
        idField.setText("");
        memIdField.setText("");
        bookIdField.setText("");
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
