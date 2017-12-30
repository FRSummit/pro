package inventorymanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutUIController implements Initializable {
    @FXML
    private Label statusBar;
    @FXML
    private ImageView imageView;
    @FXML
    private Label idBar;
    @FXML
    private Label nameBar;
    @FXML
    private Label batchBar;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label idB;
    @FXML
    private Label nameB;
    @FXML
    private Label batchB;
    @FXML
    private Label IMS;
    @FXML
    private ImageView imageview2;
    @FXML
    private Label sirName;
    @FXML
    private Label sirTitle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InfoAbout info = new InfoAbout("2014200000071", "FAYAZUR RAHMAN SUMMIT", 38);
        displayInfo(info);
    }
    
    public void displayInfo(InfoAbout info){
        idBar.setText(info.getId());
        nameBar.setText(info.getName());
        batchBar.setText("" + info.getBatch());
        imageView.setImage(info.getImage());
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleMainMenuReturnAction(ActionEvent event) {
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
    
    
}
