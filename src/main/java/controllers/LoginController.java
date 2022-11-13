package controllers;

import codes.Methods;
import com.example.copy.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import network.LoginRequestObject;
import network.LoginResponseObject;
import network.WriteThreadClient;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    private Main main;
    public Button signIn;
    public Button signUpButton;
    public Label errorLabel;
    public PasswordField password;
    public TextField productionCompanyName;

    public void loginAction(ActionEvent actionEvent) {
        String userName = productionCompanyName.getText().strip();
        String passwordText = password.getText();
        if (!userName.isEmpty() && !passwordText.isEmpty()) {
            LoginRequestObject loginRequestObject = new LoginRequestObject(userName, passwordText);
            WriteThreadClient.write(loginRequestObject);
        }
    }

    public void loginAction(LoginResponseObject loginResponseObject) {
        if (loginResponseObject.isAccess()) {
            try {
                Methods methods = Methods.getInstance(loginResponseObject);
                main.showHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        errorLabel.setText("Credentials do not match. Please try again.");
    }

    public void signUp(ActionEvent actionEvent) {
        try {
            main.showSignUpPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(Main main) {
        this.main = main;
        init();
    }

    void init() {
        productionCompanyName.clear();
        password.clear();
        errorLabel.setText(null);
        productionCompanyName.requestFocus();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
}
