package controllers;

import codes.Methods;
import codes.Movie;
import codes.ProductionCompany;
import codes.ReadWriteFile;
import com.example.copy.Updater;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import network.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMovieDialogBoxController {
    public TextField titleBox;
    public TextField genreBox;
    public TextField durationBox;
    public TextField releaseYearBox;
    public TextField budgetBox;
    public TextField revenueBox;
    public Button resetButton;
    public Button confirmButton;
    public Button cancelButton;
    public Label errorMessage;
    ProductionCompany productionCompany;
    int duration;
    int budget;
    String genres[];
    int releaseYear;
    int revenue;
    Movie movie;

    public void onConfirmClick(ActionEvent actionEvent) {
        int close = 0;
        if (titleBox.getText().isEmpty()) {
            errorMessage.setText("Please enter Title");
        } else if (genreBox.getText().isEmpty()) {
            errorMessage.setText("Please enter Genre");
        } else {
            try {
                releaseYear = Integer.parseInt(releaseYearBox.getText());
                budget = Integer.parseInt(budgetBox.getText());
                revenue = Integer.parseInt(revenueBox.getText());
                duration = Integer.parseInt(durationBox.getText());
                errorMessage.setText(null);
                genres = genreBox.getText().split(",");
                movie = new Movie(titleBox.getText(), releaseYear, genres, duration, budget, revenue, productionCompany);
                movie.setTransferValue(true);
                ReadWriteFile.getInstance().addMovie(movie);
//                System.out.println("AddMovieDialogBox: " + movie.getId() + " " + movie.isBeingTransferred()); //ok
                WriteThreadClient.write(new AddMovieObject(movie, this.productionCompany));
//                System.out.println("AddMovieDialogBox: " + movie.getTitle() + " " + movie.getYearOfRelease() + " " + movie.getProductionCompany().getName()); //ok
                close = 1;
                Node node = (Node) actionEvent.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage.setText("Please enter valid information");
            }
        }
        if (close == 1) {
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        init(this.productionCompany);
    }

    public void init(ProductionCompany productionCompany) {
        this.productionCompany = Methods.getMethods().getProductionCompany();
        errorMessage.setText(null);
        titleBox.clear();
        releaseYearBox.clear();
        durationBox.clear();
        budgetBox.clear();
        revenueBox.clear();
        genreBox.clear();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void onResetClick(ActionEvent actionEvent) {
        init(this.productionCompany);
    }
}
