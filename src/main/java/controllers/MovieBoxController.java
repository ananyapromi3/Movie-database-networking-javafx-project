package controllers;

import codes.Movie;
import com.example.copy.Updater;
import com.example.copy.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MovieBoxController {
    public Label movieTitle;
    public Button transferButton;
    public Button detailsButton;
    private Movie movie;
    private Updater updater = new Updater();

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void init(Movie movie) {
        this.movie = movie;
        updateMovieInfoUI();
    }

    public void updateMovieInfoUI() {
        movieTitle.setText(movie.getTitle());
    }

    public void onTransferClick(ActionEvent actionEvent) {
        updater.updateGUI(movie, 6);
    }

    public void onDetailsClick(ActionEvent actionEvent) throws IOException {
        updater.updateGUI(movie, 5);
    }
}
