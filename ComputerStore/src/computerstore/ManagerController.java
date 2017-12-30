/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerstore;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author F R SUMMIT
 */
public class ManagerController implements Initializable {

    @FXML
    private TableView<Manager> tableView;
    @FXML
    private TableColumn<Manager, String> idColumn;
    @FXML
    private TableColumn<Manager, String> nameColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    private ObservableList<Manager> manager;
    private Statement s;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String DB_URL = "jdbc:mysql://localhost/com_st";
        String DB_USER = "root";
        String DB_PASS = "6472";
        
        manager = FXCollections.observableArrayList();
        tableView.setItems(manager);
        
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            s = connection.createStatement();
            String quary = "select * from manager";
            ResultSet rs = s.executeQuery(quary);
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                Manager m = new Manager(id, name);
                manager.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void save(ActionEvent event){
        String id = idField.getText();
        String name = nameField.getText();
        Manager m = new Manager(id, name);
        String quary = "insert into manager values('" + m.getId() + "', '" + m.getName() +"');";
        idField.setText("");
        nameField.setText("");
        try{
            s.executeUpdate(quary);
            manager.add(m);
        }catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void reset(ActionEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        } catch (IOException ex) {
            System.out.println("Error");
        }
        Scene scene = new Scene(root);
        Stage s = ComputerStore.getStage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        Manager m = tableView.getSelectionModel().getSelectedItem();
        display(m);
    }
    
    public void display(Manager m){
        idField.setText(m.getId());
        nameField.setText(m.getName());
    }
    
}
