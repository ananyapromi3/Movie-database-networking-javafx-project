package controllers;

import codes.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class MovieDetailsController {
    public Label movieTitle;
    public Label pCompanyName;
    public Label genre;
    public Label releaseYear;
    public Label duration;
    public Label budget;
    public Label revenue;
    public Button closeButton;
    public ImageView moviePoster;
    private Movie movie;

    public void init(Movie movie, Image image) {
        this.movie = movie;
        moviePoster.setImage(image);
        updateMovieUI();
    }

    private void updateMovieUI() {
        movieTitle.setText(movie.getTitle());
        pCompanyName.setText(movie.getProductionCompany().getName());
        genre.setText(movie.getGenreStr());
        releaseYear.setText("Release Year:  " + movie.getYearOfRelease());
        duration.setText("Running Time:  " + movie.getRunningTime() + " min");
        budget.setText("Budget:  " + movie.getBudget() + "$");
        revenue.setText("Revenue:  " + movie.getRevenue() + "$");
//        profit.setText("Profit:  " + (movie.getRevenue() - movie.getBudget()) + "$");
    }

    @FXML
    void onCloseClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
}
