package com.example.pms.controllers;

import com.example.pms.entities.User;
import com.example.pms.services.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableView<User> table;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField roleTextField;

    private ServiceUser serviceUser = new ServiceUser();;
    private ObservableList<User> userList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTab();
    }

    void initializeTab() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadUsers();
    }

    private void loadUsers() {
        userList = FXCollections.observableArrayList(serviceUser.getAllUsers());
        table.setItems(userList);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fillFields(newValue);
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        if (validateInput()) {
            String name = nameTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();
            String role = roleTextField.getText();
            String password = passwordTextField.getText();

            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phone);
            newUser.setRole(role);
            newUser.setPassword(password);

            serviceUser.addUser(newUser);
            loadUsers(); // Refresh the table after adding
        }
    }

    @FXML
    void delete(ActionEvent event) {
        User selectedUser = table.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            int userId = selectedUser.getId();
            serviceUser.deleteUser(userId);
            loadUsers(); // Refresh the table after deletion
        } else {
            showAlert("Error", "Please select a user to delete.");
        }
    }

    @FXML
    void update(ActionEvent event) {
        if (validateInput()) {
            User selectedUser = table.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                selectedUser.setName(nameTextField.getText());
                selectedUser.setEmail(emailTextField.getText());
                selectedUser.setPhoneNumber(phoneTextField.getText());
                selectedUser.setRole(roleTextField.getText());
                selectedUser.setPassword(passwordTextField.getText());

                serviceUser.updateUser(selectedUser);
                loadUsers(); // Refresh the table after update
            } else {
                showAlert("Error", "Please select a user to update.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateInput() {
        if (nameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                phoneTextField.getText().isEmpty() || roleTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            showAlert("Missing Fields", "Please fill in all required fields.");
            return false;
        }
        return true;
    }
    private void fillFields(User user) {
        nameTextField.setText(user.getName());
        emailTextField.setText(user.getEmail());
        phoneTextField.setText(user.getPhoneNumber());
        roleTextField.setText(user.getRole());
        passwordTextField.setText(user.getPassword());
    }
}
