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

public class LoginController {
    @FXML
    private TextField emailTextFIeld;

    @FXML
    private PasswordField passwordTextField;

    private ServiceUser userService = new ServiceUser();

    @FXML
    void goToSignUp(ActionEvent event) {
        Stage primaryStage = (Stage) emailTextFIeld.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("SignUp.fxml"));
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
        String email = emailTextFIeld.getText();
        String password = passwordTextField.getText();

        if (!validateInput()) {
            return; // Stop execution if input validation fails
        }

        String loginResult = userService.login(email, password);
        User u = userService.findUserByEmail(email);

        if (loginResult.equals("admin")) {
            showAlert("Success", "Login successful as admin.");
            Stage primaryStage = (Stage) emailTextFIeld.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("admin.fxml"));
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
        } else if (loginResult.equals("user")) {
            showAlert("Success", "Login successful as user.");
            Stage primaryStage = (Stage) emailTextFIeld.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("properties.fxml"));

            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return; // Handle the exception
            }
            System.out.println(u.getId());
            PropertiesController propertiesController = loader.getController();
            propertiesController.setUser(u.getId());
            // Create a new scene with the loaded root
            Scene scene = new Scene(root);

            // Set the scene to the primary stage
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            showAlert("Error", "Invalid email or password.");
        }

        // Clear the password field after login attempt
        passwordTextField.clear();
    }

    private boolean validateInput() {
        if (emailTextFIeld.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
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
