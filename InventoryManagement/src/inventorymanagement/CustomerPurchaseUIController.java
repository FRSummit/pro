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
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerPurchaseUIController implements Initializable {
    @FXML
    private TableView<CustomerPurchase> customerPurchaseTableView;
    @FXML
    private TableColumn<CustomerPurchase, String> customerIdColumn;
    @FXML
    private TableColumn<CustomerPurchase, String> productidColumn;
    @FXML
    private TableColumn<CustomerPurchase, String> dateColumn;
    @FXML
    private TableColumn<CustomerPurchase, Number> quantityColumn;
    @FXML
    private TableColumn<CustomerPurchase, Number> purchasePriceColumn;
    @FXML
    private TableColumn<CustomerPurchase, String> purchaseTypeColumn;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField productIdField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<CustomerPurchaseType> purchaseTypeSelection;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label statusBar;
    private Statement statement;
    private ObservableList<CustomerPurchase>customerPurchase;
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
        date.setValue(LocalDate.now());
        purchaseTypeSelection.getItems().addAll(CustomerPurchaseType.values());
        customerPurchase = FXCollections.observableArrayList();
        customerPurchaseTableView.setItems(customerPurchase);
        customerIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerId()));
        productidColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductId()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQuantity()));
        purchasePriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));
        purchaseTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String query = "select * from customer_purchase";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String customerId = resultSet.getString("customer_id");
                String productId = resultSet.getString("product_id");
                String dates = resultSet.getString("date");
                String tokens[] = dates.split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                double quantity = resultSet.getDouble("quantity");
                double price = resultSet.getDouble("parchase_price");
                CustomerPurchaseType type = CustomerPurchaseType.valueOf(resultSet.getString("due_paid"));
                CustomerPurchase purchase = new CustomerPurchase(customerId, productId, date, quantity, price, type);
                customerPurchase.add(purchase);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connecting to the database");
            alert.setHeaderText("Purchase connection failed");
            alert.setContentText("Failed to connect CustomerPurchase");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(CustomerPurchaseUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void displayParchase(CustomerPurchase purchase){
        customerIdField.setText(purchase.getCustomerId());
        productIdField.setText(purchase.getProductId());
        datePicker.setValue(purchase.getDate());
        quantityField.setText("" + purchase.getQuantity());
        priceField.setText("" + purchase.getPrice());
        purchaseTypeSelection.getSelectionModel().clearSelection();
        purchaseTypeSelection.setValue(purchase.getType());
        statusBar.setText("Showing " +customerIdField.getText() + " & " + productIdField.getText() + " purchase information");
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        CustomerPurchase selectedParchase = customerPurchaseTableView.getSelectionModel().getSelectedItem();
        displayParchase(selectedParchase);
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String customerId = customerIdField.getText();
        String productId = productIdField.getText();
        LocalDate date = datePicker.getValue();
        double quantity = Double.parseDouble(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        CustomerPurchaseType type = purchaseTypeSelection.getSelectionModel().getSelectedItem();
        CustomerPurchase purchase = new CustomerPurchase(customerId, productId, date, quantity, price, type);
        String query = "insert into customer_purchase values('" + purchase.getCustomerId() + "', '" + purchase.getProductId() + "', '" + purchase.getDate()
                + "', " + purchase.getQuantity() + ", " + purchase.getPrice() + ", '" + purchase.getType() + "');";
        customerIdField.setText("");
        productIdField.setText("");
        datePicker.setValue(null);
        quantityField.setText("");
        priceField.setText("");
        purchaseTypeSelection.getSelectionModel().select(null);
        try {
            statement.executeUpdate(query);
            statusBar.setText("Inserted parchase information into the database");
            customerPurchase.add(purchase);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inserting Product");
            alert.setHeaderText("Product insertion failed");
            alert.setContentText("Failed to insert Product");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(CustomerPurchaseUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        customerIdField.setText("");
        productIdField.setText("");
        datePicker.setValue(null);
        quantityField.setText("");
        priceField.setText("");
        purchaseTypeSelection.getSelectionModel().select(null);
        statusBar.setText("New form is ready");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        String query = "delete from customer_parchase where customer_id = " + customerIdField.getId() + ";";
        
        int indexToRemove = customerPurchaseTableView.getSelectionModel().getSelectedIndex();
        customerPurchase.remove(indexToRemove);
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
