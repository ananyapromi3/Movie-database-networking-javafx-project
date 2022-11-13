package com.example.copy;

import codes.Methods;
import controllers.HomepageController;
import controllers.LoginController;
import controllers.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.Client;
import network.TerminateServerObject;
import network.NetworkUtil;
import network.ReadThreadClient;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    private Client client;
    private NetworkUtil networkUtil;
    private ReadThreadClient readThreadClient;
    private UpdateFromNetwork updateFromNetwork = new UpdateFromNetwork();

    @Override
    public void start(Stage primaryStage) throws Exception {
        String serverAddress = "127.0.0.1";
        int serverPort = 12346;
        client = new Client(serverAddress, serverPort);
        networkUtil = client.getNetworkUtil();
        readThreadClient = client.getReadThreadClient();
        readThreadClient.setUpdateFromReadThread(updateFromNetwork);
        stage = primaryStage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> closeProgram());
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml-login-page.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.init(this);
        updateFromNetwork.setLoginController(controller);
        stage.setTitle("Production Company - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showSignUpPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml-sign-up-page.fxml"));
        Parent root = loader.load();
        SignUpController controller = loader.getController();
        controller.init(this);
        updateFromNetwork.setSignUpController(controller);
        stage.setTitle("Production Company - Sign Up");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml-home-page.fxml"));
        Parent root = loader.load();
        HomepageController controller = loader.getController();
        controller.init(this);
        stage.setTitle("Production Company - " + Methods.getMethods().getProductionCompany().getName());
        stage.setScene(new Scene(root));
        stage.show();
    }

    void closeProgram() {
        try {
            networkUtil.write(new TerminateServerObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}