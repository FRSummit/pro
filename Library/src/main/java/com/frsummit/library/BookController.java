/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.library;

import com.frsummit.library.model.Author;
import com.frsummit.library.model.Book;
import com.frsummit.library.model.Catagory;
import com.frsummit.library.model.Publisher;
import com.frsummit.library.model.Supplier;
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
public class BookController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField pubId;
    @FXML
    private TextField supId;
    @FXML
    private TextField catId;
    @FXML
    private TextField auId;
    @FXML
    private ListView<Book> listView;
    private ObservableList<Book> bookList;
    private List<Author> authorList = new ArrayList<>();
    private List<Catagory> catList = new ArrayList<>();
    private List<Supplier> supList = new ArrayList<>();
    private List<Publisher> pubList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookList = FXCollections.observableArrayList();
        listView.setItems(bookList);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        bookList.addAll(session.createCriteria(Book.class).list());
        session.close();
        
        Author au = new Author();
        authorList = au.getAuthorss();
        List<String> aut = new ArrayList<>();
        authorList.forEach((rr) -> {aut.add(rr.getId() + "");});
        
        Catagory ct = new Catagory();
        catList = ct.getCataagories();
        List<String> cat = new ArrayList<>();
        catList.forEach((rr) -> {cat.add(rr.getId() + "");});
        
        Supplier su = new Supplier();
        supList = su.getSupliers();
        List<String> sup = new ArrayList<>();
        supList.forEach((rr) -> {sup.add(rr.getId() + "");});
        
        Publisher pu = new Publisher();
        pubList = pu.getPublishers();
        List<String> pub = new ArrayList<>();
        pubList.forEach((rr) -> {pub.add(rr.getId() + "");});
    }    

    @FXML
    private void save(ActionEvent event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        String id = idField.getText();
        String name = nameField.getText();
        String fn = pubId.getText();
        String dep = supId.getText();
        String phone = catId.getText();
        String age = auId.getText();
        
        Author aut = null;
        for (Author a : authorList) {
            if (a.getId() == age) {
                aut = a;
            }
        }
        
        Catagory cat = null;
        for (Catagory c : catList) {
            if (c.getId() == phone) {
                cat = c;
            }
        }
        
        Supplier sup = null;
        for (Supplier s : supList) {
            if (s.getId() == dep) {
                sup = s;
            }
        }
        
        Publisher pub = null;
        for (Publisher p : pubList) {
            if (p.getId() == fn) {
                pub = p;
            }
        }
        
        Book s = new Book(id, name,pub, sup, cat, aut);
        bookList.add(s);
        
        session.save(s);
        tr.commit();
        session.close();
        
        idField.setText("");
        nameField.setText("");
        pubId.setText("");
        supId.setText("");
        catId.setText("");
        auId.setText("");
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
