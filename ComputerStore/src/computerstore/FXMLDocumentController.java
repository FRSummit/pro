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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author F R SUMMIT
 */
public class FXMLDocumentController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String DB_URL = "jdbc:mysql://localhost/com_st";
        String DB_USER = "root";
        String DB_PASS = "6472";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.print("err");
        }
    }    

    @FXML
    private void manager(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        } catch (IOException ex) {
            System.out.println("Error");
        }
        Scene scene = new Scene(root);
        Stage s = ComputerStore.getStage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void customer(ActionEvent event) {
    }
    
}
