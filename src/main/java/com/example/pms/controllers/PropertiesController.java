package com.example.pms.controllers;

import com.example.pms.entities.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.example.pms.services.ServiceProperties;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PropertiesController implements Initializable {

    @FXML
    private TableColumn<Properties, String> nameColumn;

    @FXML
    private TableColumn<Properties, String> locationColumn;

    @FXML
    private TableColumn<Properties, Double> priceColumn;

    @FXML
    private TableColumn<Properties, Date> availableFromColumn;

    @FXML
    private TableColumn<Properties, Date> availableUntilColumn;

    @FXML
    private TableColumn<Properties, String> tenantNameColumn;

    @FXML
    private TableColumn<Properties, String> tenantNumberColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField availableFromTextField;

    @FXML
    private TextField availableUntilTextField;

    @FXML
    private TextField tenantNameTextField;

    @FXML
    private TextField tenantNumberTextField;

    @FXML
    private TableView<Properties> table;

    private ServiceProperties serviceProperties= new ServiceProperties();

    private int userId;
    private ObservableList<Properties> propertiesList;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void setUser(int userId) {
        this.userId = userId;
        loadProperties();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTab();
        loadProperties();
    }
    @FXML
    void initializeTab() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerMonth"));
        availableFromColumn.setCellValueFactory(new PropertyValueFactory<>("availableFrom"));
        availableUntilColumn.setCellValueFactory(new PropertyValueFactory<>("availableUntil"));
        tenantNameColumn.setCellValueFactory(new PropertyValueFactory<>("tenantName"));
        tenantNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tenantNumber"));
    }

    private void loadProperties() {
        propertiesList = FXCollections.observableArrayList(serviceProperties.getPropertiesByUserId(userId));
        System.out.println("User id:" + userId);
        System.out.println("Properties");
        serviceProperties.getPropertiesByUserId(userId).forEach(System.out::println);
        table.setItems(propertiesList);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fillFields(newValue);
            }
        });
    }

    @FXML
    void add(ActionEvent event) throws ParseException {
        String name = nameTextField.getText();
        String location = locationTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        Date availableFrom = dateFormat.parse(availableFromTextField.getText());
        Date availableUntil = dateFormat.parse(availableUntilTextField.getText());
        String tenantName = tenantNameTextField.getText();
        String tenantNumber = tenantNumberTextField.getText();
        String description = descriptionTextField.getText();

        Properties newProperty = new Properties();
        newProperty.setName(name);
        newProperty.setLocation(location);
        newProperty.setPricePerMonth(price);
        newProperty.setAvailableFrom(availableFrom);
        newProperty.setAvailableUntil(availableUntil);
        newProperty.setTenantName(tenantName);
        newProperty.setTenantNumber(tenantNumber);
        newProperty.setDescription(description);
        newProperty.setUserId(userId);

        serviceProperties.addProperty(newProperty);
        loadProperties(); // Refresh the table after adding
    }

    @FXML
    void delete(ActionEvent event) {
        Properties selectedProperty = table.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            int propertyId = selectedProperty.getId();
            serviceProperties.deleteProperty(propertyId);
            loadProperties(); // Refresh the table after deletion
        } else {
            showAlert("Error", "Please select a property to delete.");
        }
    }

    @FXML
    void update(ActionEvent event) throws ParseException {
        Properties selectedProperty = table.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            selectedProperty.setName(nameTextField.getText());
            selectedProperty.setLocation(locationTextField.getText());
            selectedProperty.setPricePerMonth(Double.parseDouble(priceTextField.getText()));
            selectedProperty.setAvailableFrom(dateFormat.parse(availableFromTextField.getText()));
            selectedProperty.setAvailableUntil(dateFormat.parse(availableUntilTextField.getText()));
            selectedProperty.setTenantName(tenantNameTextField.getText());
            selectedProperty.setTenantNumber(tenantNumberTextField.getText());

            serviceProperties.updateProperty(selectedProperty);
            loadProperties(); // Refresh the table after update
        } else {
            showAlert("Error", "Please select a property to update.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void fillFields(Properties property) {
        nameTextField.setText(property.getName());
        locationTextField.setText(property.getLocation());
        priceTextField.setText(String.valueOf(property.getPricePerMonth()));
        descriptionTextField.setText(property.getDescription());
        availableFromTextField.setText(property.getAvailableUntil() != null ? property.getAvailableUntil().toString() : "");
        availableUntilTextField.setText(property.getAvailableUntil() != null ? property.getAvailableUntil().toString() : "");
        tenantNameTextField.setText(property.getTenantName());
        tenantNumberTextField.setText(property.getTenantNumber());
    }

}

