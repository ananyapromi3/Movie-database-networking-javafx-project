package controllers;

import codes.Methods;
import codes.Movie;
import codes.ProductionCompany;
import codes.ReadWriteFile;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import network.TransferRequestObject;
import network.WriteThreadClient;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransferMovieController implements Initializable {
    public Label movieTitle;
    public Label errorLabel;
    public Button cancelButton;
    public Button confirmButton;
    public ChoiceBox<String> productionCompanyList;
    private Movie movie;
    private ProductionCompany productionCompany;

    public void cancel(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void confirm(ActionEvent actionEvent) {
        WriteThreadClient.write(new TransferRequestObject(movie.getId(), movie.getProductionCompany().getId(), productionCompany));
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void init(Movie movie) {
        this.movie = movie;
        movieTitle.setText(movie.getTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ProductionCompany> productionCompanies = ReadWriteFile.getInstance().getProductionCompanies();
        for (int i = 0; i < productionCompanies.size(); i++) {
            if (!productionCompanies.get(i).getName().isEmpty() && Methods.getMethods().getProductionCompany().getId() != productionCompanies.get(i).getId()) {
                productionCompanyList.getItems().add(productionCompanies.get(i).getName());
            }
        }
        productionCompanyList.setOnAction(this::getProductionCompany);
    }

    public void getProductionCompany(ActionEvent actionEvent) {
        String productionCompanyName = productionCompanyList.getValue();
        ProductionCompany tempCompany = new ProductionCompany(productionCompanyName);
        this.productionCompany = tempCompany;
    }
}
