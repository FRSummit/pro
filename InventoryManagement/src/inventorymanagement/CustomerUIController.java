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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerUIController implements Initializable {
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, String> idColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> typeColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea addressArea;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<CustomerType> typeBox;
    @FXML
    private Label statusBar;
    private Statement statement;
    private ObservableList<Customer>customers;
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
        typeBox.getItems().addAll(CustomerType.values());
        customers = FXCollections.observableArrayList();
        customerTableView.setItems(customers);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String query = "select * from customer";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                CustomerType type = CustomerType.valueOf(resultSet.getString("type"));
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id, name, type, phone, email, address);
                customers.add(customer);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connecting to the database");
            alert.setHeaderText("Customer connection failed");
            alert.setContentText("Failed to connect Customer");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(SupplierUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String id = idField.getText();
        String name = nameField.getText();
        CustomerType type = typeBox.getSelectionModel().getSelectedItem();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String address = addressArea.getText();
        Customer customer = new Customer(id, name, type, phone, email, address);
        String query = "insert into customer values('" + customer.getId() + "', '" + customer.getName() + "', '" + customer.getType() 
                + "', '" + customer.getPhone() + "', '" + customer.getEmail() + "', '" + customer.getAddress() + "');";
        idField.setText("");
        nameField.setText("");
        typeBox.getSelectionModel().select(null);
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        try {
            statement.executeUpdate(query);
            statusBar.setText("Inserted " + customer.getName() + " into Customer list into database");
            customers.add(customer);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inserting Customer");
            alert.setHeaderText("Customer insertion failed");
            alert.setContentText("Failed to insert Customer " + customer.getName());
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(SupplierUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private void displayCustomer(Customer customer){
        idField.setText(customer.getId());
        nameField.setText(customer.getName());
        typeBox.getSelectionModel().select(customer.getType());
        phoneField.setText(customer.getPhone());
        emailField.setText(customer.getEmail());
        addressArea.setText(customer.getAddress());
        statusBar.setText("Showing " + nameField.getText() + "'s information");
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        displayCustomer(selectedCustomer);
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        idField.setText("");
        nameField.setText("");
        typeBox.getSelectionModel().select(null);
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        statusBar.setText("New form is ready");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        String query = "delete from customerc where id = " + idField.getId() + ";";
        
        int indexToRemove = customerTableView.getSelectionModel().getSelectedIndex();
        customers.remove(indexToRemove);
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
    
}
