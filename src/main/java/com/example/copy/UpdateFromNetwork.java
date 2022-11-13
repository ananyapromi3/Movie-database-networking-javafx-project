package com.example.copy;

import codes.Movie;
import codes.Methods;
import controllers.HomepageController;
import controllers.LoginController;
import controllers.SignUpController;
import javafx.application.Platform;
import network.LoginResponseObject;
import network.UpdateRespond;

public class UpdateFromNetwork {
    private HomepageController homepageController;
    private LoginController loginController;
    private SignUpController signUpController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setSignUpController(SignUpController signUpController) {
        this.signUpController = signUpController;
    }

    public void loginAction(LoginResponseObject loginResponseObject) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginController.loginAction(loginResponseObject);
            }
        });
    }

    public synchronized void updateFromServerRespond(UpdateRespond updateRespond) {
        homepageController = Methods.getMethods().getHomepageController();
        int refresher = 0;
        if (updateRespond.getTransferOrReceive() == 1) {
            Movie movie = updateRespond.getMovie();
            updateRespond.getMovie().setProductionCompany(updateRespond.getReceiverProductionCompany());
            updateRespond.getReceiverProductionCompany().addMovie(updateRespond.getMovie());
            Methods.getMethods().addToTempMovieList(movie);
            if (updateRespond.getTransferCompanyID() == Methods.getMethods().getProductionCompany().getId()) {
                Methods.getMethods().removeFromProductionCompany(updateRespond.getMovie());
                refresher = 1;
            }
        } else if (updateRespond.getTransferOrReceive() == 2) {
            Methods.getMethods().removeFromTempList(updateRespond.getMovie());
            if (updateRespond.getReceiverCompanyID() == Methods.getMethods().getProductionCompany().getId()) {
                Methods.getMethods().addToList(updateRespond.getMovie());
                updateRespond.getReceiverProductionCompany().addMovie(updateRespond.getMovie());
//                System.out.println(updateRespond.getMovie().getTitle());
                refresher = 1;
            }
        } else {
//            System.out.println("In updateFromServerRespond, UpdateFromNetwork: in else");
            if (updateRespond.getReceiverProductionCompany().getId() == Methods.getMethods().getProductionCompany().getId()) {
//            System.out.println("in if"); //Problem here
//            System.out.println(updateRespond.getReceiverProductionCompany().getId() + " " + Methods.getInstance().getProductionCompany().getId());
                Methods.getMethods().addToList(updateRespond.getMovie());
//            System.out.println(updateRespond.getMovie().getTitle());
                refresher = 1;
            }
        }

        int finalRefresher = refresher;
        homepageController.getUpdater().refreshGUI(finalRefresher);
        homepageController.getUpdater().refreshGUI(2);
    }

    public void signUpAction(LoginResponseObject loginResponseObject) {
        Platform.runLater(() -> signUpController.signUpAction(loginResponseObject));
    }
}
