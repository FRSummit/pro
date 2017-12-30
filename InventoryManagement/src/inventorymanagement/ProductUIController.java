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

public class ProductUIController implements Initializable {
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> typeColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> menufactureColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField menufacturCodeField;
    @FXML
    private ComboBox<ProductType> typeSelection;
    @FXML
    private ComboBox<ProductCategory> categorySelection;
    @FXML
    private Label statusBar;
    private Statement statement;
    private ObservableList<Product>products;
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
        typeSelection.getItems().addAll(ProductType.values());
        categorySelection.getItems().addAll(ProductCategory.values());
        products = FXCollections.observableArrayList();
        productTableView.setItems(products);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().toString()));
        menufactureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenufacture()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String query = "select * from products";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                ProductType type = ProductType.valueOf(resultSet.getString("type"));
                ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));
                String menufacture = resultSet.getString("menu_code");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("unit_price");
                Product product = new Product(id, name, type, category, menufacture, description, price);
                products.add(product);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connecting to the database");
            alert.setHeaderText("Product connection failed");
            alert.setContentText("Failed to connect Product");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(ProductUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String id = idField.getText();
        String name = nameField.getText();
        ProductType type = typeSelection.getSelectionModel().getSelectedItem();
        ProductCategory category = categorySelection.getSelectionModel().getSelectedItem();
        String menufacture = menufacturCodeField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        Product product = new Product(id, name, type, category, menufacture, description, price);
        String query = "insert into products values('" + product.getId() + "', '" + product.getName() + "', '" + product.getType()
                + "', '" + product.getCategory() + "', '" + product.getMenufacture() + "', '" + product.getDescription() + "', " + product.getPrice() + ");";
        idField.setText("");
        nameField.setText("");
        typeSelection.getSelectionModel().select(null);
        categorySelection.getSelectionModel().select(null);
        menufacturCodeField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        try {
            statement.executeUpdate(query);
            statusBar.setText("Inserted" + product.getName() + "into Product into database");
            products.add(product);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inserting Product");
            alert.setHeaderText("Product insertion failed");
            alert.setContentText("Failed to insert Product");
            alert.showAndWait();
            System.exit(0);
            Logger.getLogger(ProductUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        idField.setText("");
        nameField.setText("");
        typeSelection.getSelectionModel().select(null);
        categorySelection.getSelectionModel().select(null);
        menufacturCodeField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        statusBar.setText("New form is ready");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        String query = "delete from products where id = " + idField.getId() + ";";
        
        int indexToRemove = productTableView.getSelectionModel().getSelectedIndex();
        products.remove(indexToRemove);
    }

    @FXML
    private void handleReturnManiMenuAction(ActionEvent event) {
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
    
    private void displayProduct(Product product){
        idField.setText(product.getId());
        nameField.setText(product.getName());
        typeSelection.getSelectionModel().select(product.getType());
        categorySelection.getSelectionModel().select(product.getCategory());
        menufacturCodeField.setText(product.getMenufacture());
        descriptionField.setText(product.getDescription());
        priceField.setText("" + product.getPrice());
        statusBar.setText("Showing "+ idField.getText() + " - " + nameField.getText() +"'s product information");
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        displayProduct(selectedProduct);
    }
    
}
