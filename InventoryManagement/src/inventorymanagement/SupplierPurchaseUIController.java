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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupplierPurchaseUIController implements Initializable {
    @FXML
    private TableView<SupplierPurchase> supplierPurchaseTableView;
    @FXML
    private TableColumn<SupplierPurchase, String> supplierIdColumn;
    @FXML
    private TableColumn<SupplierPurchase, String> productIdColumn;
    @FXML
    private TableColumn<SupplierPurchase, String> dateColumn;
    @FXML
    private TableColumn<SupplierPurchase, Number> quantityColumn;
    @FXML
    private TableColumn<SupplierPurchase, Number> priceColumn;
    @FXML
    private TextField supplierIdField;
    @FXML
    private TextField productIdField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private DatePicker datePicker;
    private Statement statement;
    private ObservableList<SupplierPurchase>supplies;
    @FXML
    private Label statusBar;
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
        String DB_PASS = "6472";
        
        date.setValue(LocalDate.now());
        supplies = FXCollections.observableArrayList();
        supplierPurchaseTableView.setItems(supplies);
        supplierIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplierId()));
        productIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductId()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQuantity()));
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String query = "select * from supplier_parchase";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String supplierId = resultSet.getString("supplier_id");
                String productId = resultSet.getString("product_id");
                String dates = resultSet.getString("supply_date");
                String tokens[] = dates.split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                double quantity = resultSet.getDouble("quantity");
                double price = resultSet.getDouble("parchase_price");
                SupplierPurchase supply = new SupplierPurchase(supplierId, productId, date, quantity, price);
                supplies.add(supply);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connecting to the database");
            alert.setHeaderText("Supplier purchase connection failed");
            alert.setContentText("Failed to connect SupplierPurchase");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(SupplierUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String supplierId = supplierIdField.getText();
        String productId = productIdField.getText();
        LocalDate date = datePicker.getValue();
        double quantity = Double.parseDouble(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        SupplierPurchase supply = new SupplierPurchase(supplierId, productId, date, quantity, price);
        String query = "insert into supplier_parchase values('" + supply.getSupplierId() + "', '" + supply.getProductId() + "', '" + supply.getDate()
                + "', " + supply.getQuantity() + ", " + supply.getPrice() + ");";
        supplierIdField.setText("");
        productIdField.setText("");
        datePicker.setValue(null);
        quantityField.setText("");
        priceField.setText("");
        try {
            statement.executeUpdate(query);
            statusBar.setText("Inserted into SypplierPurchase into database");
            supplies.add(supply);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inserting Supplier");
            alert.setHeaderText("SupplierPurchase insertion failed");
            alert.setContentText("Failed to insert SupplierPurchase");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(SupplierPurchaseUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        supplierIdField.setText("");
        productIdField.setText("");
        datePicker.setValue(null);
        quantityField.setText("");
        priceField.setText("");
        statusBar.setText("New form is ready");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        String query = "delete from supplier_parchase where supplier_id = " + supplierIdField.getId() + ";";
        
        int indexToRemove = supplierPurchaseTableView.getSelectionModel().getSelectedIndex();
        supplies.remove(indexToRemove);
        
        supplierIdField.setText("");
        productIdField.setText("");
        datePicker.setValue(null);
        quantityField.setText("");
        priceField.setText("");
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
    
    private void displaySupplies(SupplierPurchase supply){
        supplierIdField.setText(supply.getSupplierId());
        productIdField.setText(supply.getProductId());
        datePicker.setValue(supply.getDate());
        quantityField.setText("" + supply.getQuantity());
        priceField.setText("" + supply.getPrice());
        statusBar.setText("Showing "+ supplierIdField.getText() + " & " + productIdField.getText() +" purchase information");
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        SupplierPurchase selectedSupplierParchase = supplierPurchaseTableView.getSelectionModel().getSelectedItem();
        displaySupplies(selectedSupplierParchase);
    }
    
}
