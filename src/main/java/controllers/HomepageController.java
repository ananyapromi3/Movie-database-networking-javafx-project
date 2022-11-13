package controllers;

import codes.Methods;
import codes.Movie;
import codes.ProductionCompany;
import com.example.copy.Updater;
import com.example.copy.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import network.LogoutRequestObject;
import network.WriteThreadClient;

import java.util.ArrayList;
import java.util.List;

public class HomepageController {
    public Label notFoundLabel;
    public ScrollPane scrollPane;
    public GridPane grid;
    public Button mostRecentFilter;
    public TextField searchBar;
    public Button searchButton;
    public Button extra;
    private int searchOption = 0;
    private String searchString;
    private Main main;
    Updater updater;
    ProductionCompany productionCompany;
    public Label productionCompanyNameLabel;
    public Button homeButton;
    public Button logoutButton;
    public Button maxRevenueButton;
    public Button totalProfitButton;
    public Button addMovie;
    public ImageView pCompanyLogo;

    public void init(Main main) {
        this.main = main;
        productionCompany = Methods.getMethods().getProductionCompany();
        updater = new Updater(this);
        Methods.getMethods().setHomepageController(this);
        showHome(new ActionEvent());
    }

    void search() {
        updater.updateGUI(searchOption, searchString);
    }

    public Updater getUpdater() {
        return this.updater;
    }

    public GridPane getGrid() {
        return grid;
    }

    public Label getNotFoundLabel() {
        return notFoundLabel;
    }

    public void showHome(ActionEvent actionEvent) {
        productionCompanyNameLabel.setText(productionCompany.getName());
//        Image logo = new Image(getClass().getResourceAsStream("pCompany.jpeg"));
//        pCompanyLogo.setImage(logo);
        searchBar.setText(null);
        updater.updateGUI(1);
    }

    public void logout(ActionEvent actionEvent) {
        WriteThreadClient.write(new LogoutRequestObject());
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void maxRevenueFilter(ActionEvent actionEvent) {
        searchOption = 2;
        search();
    }

    public void mostRecentMovies(ActionEvent actionEvent) {
        searchOption = 1;
        search();
    }

    public void totalProfit(ActionEvent actionEvent) {
        searchOption = 4;
        search();
    }

    public void addMovie(ActionEvent actionEvent) {
        searchOption = 10;
        search();
//        System.out.println("ADD MOVIE");
    }

    public void onSearchClick(ActionEvent actionEvent) {
        searchOption = 7;
        searchString = searchBar.getText();
        search();
    }

    public void showHome2(ActionEvent actionEvent) {
        productionCompanyNameLabel.setText(productionCompany.getName());
        searchBar.setText(null);
        updater.updateGUI(1);
    }
}
