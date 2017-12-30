package inventorymanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SubmitedToUIController implements Initializable {
    @FXML
    private ImageView imageView2;
    @FXML
    private Label sirName;
    @FXML
    private Label sirTitle;
    @FXML
    private Label IMS;
    @FXML
    private Label statusBar;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button closeButon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubmitedTo inf = new SubmitedTo("MONIRUL HASAN", "Co-ordinator & Lacturar (SOUTHEAST UNIVERSITY)");
        displayInf(inf);
    }    
    
    public void displayInf(SubmitedTo inf){
        sirName.setText(inf.getName());
        sirTitle.setText(inf.getTitle());
        imageView2.setImage(inf.getImages());
    }

    @FXML
    private void handleReturnMainMenuAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connecting to the Main Menu");
            alert.setHeaderText("Authentication failed");
            alert.setContentText("There's something problem to return main menu");
            alert.showAndWait();
            System.exit(1);
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        
        Stage stage = InventoryManagement.getStage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        System.exit(0);
    }
    
}
