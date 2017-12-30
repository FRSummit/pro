package inventorymanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupplierUIController implements Initializable {
    @FXML
    private TableColumn<Supplier, String> idColumn;
    @FXML
    private TableColumn<Supplier, String> nameColumn;
    @FXML
    private TableColumn<Supplier, String> typeColumn;
    @FXML
    private TableColumn<Supplier, String> phoneColumn;
    @FXML
    private TableColumn<Supplier, String> emailColumn;
    @FXML
    private TableColumn<Supplier, String> addressColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Label statusBar;
    @FXML
    private TableView<Supplier> supplierTableView;
    private Statement statement;
    private ObservableList<Supplier>suppliers;
    @FXML
    private TextField typeField;
    @FXML
    private Button saveButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Label infoUpdate;
    @FXML
    private DatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String DB_URL = "jdbc:mysql://localhost/inventorymanagementsystem";
        String DB_USER = "root";
        String DB_PASS = "nipa";
        
        date.setValue(LocalDate.now());
        suppliers = FXCollections.observableArrayList();
        supplierTableView.setItems(suppliers);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String query = "select * from supplier";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Supplier supplier = new Supplier(id, name, type, phone, email, address);
                suppliers.add(supplier);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connecting to the database");
            alert.setHeaderText("Supplier connection failed");
            alert.setContentText("Failed to connect Supplier");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(SupplierUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String id = idField.getText();
        String name = nameField.getText();
        String type = typeField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String address = addressArea.getText();
        Supplier supplier = new Supplier(id, name, type, phone, email, address);
        String query = "insert into supplier values('" + supplier.getId() + "', '" + supplier.getName() + "', '" + supplier.getType() 
                + "', '" + supplier.getPhone() + "', '" + supplier.getEmail() + "', '" + supplier.getAddress() + "');";
        idField.setText("");
        nameField.setText("");
        typeField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        try {
            statement.executeUpdate(query);
            statusBar.setText("Inserted " + supplier.getName() + " into Sypplier list into database");
            suppliers.add(supplier);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inserting Supplier");
            alert.setHeaderText("Supplier insertion failed");
            alert.setContentText("Failed to insert Supplier " + supplier.getName());
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(SupplierUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        idField.setText("");
        nameField.setText("");
        typeField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        statusBar.setText("New form is ready");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        String query = "delete from supplier where id = " + idField.getId() + ";";
        
        int indexToRemove = supplierTableView.getSelectionModel().getSelectedIndex();
        suppliers.remove(indexToRemove);
        
        idField.setText("");
        nameField.setText("");
        typeField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
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
    
    private void displaySupplierInfo(Supplier supplier){
        idField.setText(supplier.getId());
        nameField.setText(supplier.getName());
        typeField.setText(supplier.getType());
        phoneField.setText(supplier.getPhone());
        emailField.setText(supplier.getEmail());
        addressArea.setText(supplier.getAddress());
        statusBar.setText("Showing " + nameField.getText() + "'s information");
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        Supplier selectedSupplier = supplierTableView.getSelectionModel().getSelectedItem();
        displaySupplierInfo(selectedSupplier);
    }
    
}
