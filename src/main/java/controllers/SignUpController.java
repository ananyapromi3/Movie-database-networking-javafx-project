package controllers;

import codes.Methods;
import com.example.copy.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import network.LoginResponseObject;
import network.SignUpRequestObject;
import network.WriteThreadClient;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private Main main;
    public Button signUp;
    public Button reset;
    public Label errorLabel;
    public PasswordField password;
    public TextField productionCompanyName;
    public PasswordField password2;
    public Button backButton;

    public void back(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUpAction(ActionEvent actionEvent) {
        String userName = productionCompanyName.getText().strip();
        String passwordText = password.getText();
        String passwordText2 = password2.getText();
        if (userName.isEmpty() || passwordText.isEmpty() || passwordText2.isEmpty()) {
            return;
        }
        if (!passwordText.equals(passwordText2)) {
            errorLabel.setText("Password did not match. Try again.");
            return;
        }
        WriteThreadClient.write(new SignUpRequestObject(userName, passwordText));
    }

    public void signUpAction(LoginResponseObject loginResponseObject) {
        if (loginResponseObject.isAccess()) {
            try {
                Methods.getInstance(loginResponseObject);
                main.showHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        errorLabel.setText("Production Company " + productionCompanyName.getText() + " already exists.");
    }

    public void init(Main main) {
        this.main = main;
        init();
    }

    void init() {
        productionCompanyName.clear();
        password.clear();
        password2.clear();
        errorLabel.setText(null);
        productionCompanyName.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    public void resetAction(ActionEvent actionEvent) {
        init();
    }
}
