package com.example.pms.controllers;

import com.example.pms.MainApplication;
import com.example.pms.entities.User;
import com.example.pms.services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signupController {
    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextField;

    private ServiceUser userService = new ServiceUser();

    @FXML
    void goToLogin(ActionEvent event) {
        Stage primaryStage = (Stage) nameTextField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Login.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Handle the exception
        }

        // Create a new scene with the loaded root
        Scene scene = new Scene(root);

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void submit(ActionEvent event) {
        if (!validateInput()) {
            return; // Stop execution if input validation fails
        }

        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String phone = phoneTextField.getText();

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole("user");
        newUser.setPhoneNumber(phone);

        userService.addUser(newUser);

        // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("User created successfully!");
        alert.showAndWait();

        // Clear the form fields after successful signup
        nameTextField.clear();
        emailTextField.clear();
        passwordTextField.clear();
        phoneTextField.clear();

        Stage primaryStage = (Stage) nameTextField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("properties.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Handle the exception
        }
        PropertiesController propertiesController = loader.getController();
        propertiesController.setUser(userService.findUserByEmail(email).getId());
        // Create a new scene with the loaded root
        Scene scene = new Scene(root);

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private boolean validateInput() {
        if (nameTextField.getText().isEmpty() ||
                emailTextField.getText().isEmpty() ||
                phoneTextField.getText().isEmpty() ||
                passwordTextField.getText().isEmpty()) {
            showAlert("Missing Fields", "Please fill in all required fields.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}