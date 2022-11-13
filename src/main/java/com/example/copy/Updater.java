package com.example.copy;

import codes.Methods;
import codes.Movie;
import codes.ProductionCompany;
import controllers.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class Updater {
    HomepageController homepageController;
    Methods methods;
    int filter = 0;
    String string = null;
    ProductionCompany productionCompany;
    Dialog dialog = new Dialog();
    TotalProfitDialogController totalProfitDialogController = null;
    MovieDetailsController movieDetailsController = null;
    TransferMovieController transferMovieController = null;
    AddMovieDialogBoxController addMovieDialogBoxController = null;
    Movie movie;

    public Updater() {
    }

    public void setList(int i) {
        methods.setListToShow(i);
    }

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public Methods getLocalDatabase() {
        return methods;
    }

    public void setLocalDatabase(Methods methods) {
        this.methods = methods;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public TotalProfitDialogController getTotalProfitDialog() {
        return totalProfitDialogController;
    }

    public void setTotalProfitDialog(TotalProfitDialogController totalProfitDialogController) {
        this.totalProfitDialogController = totalProfitDialogController;
    }

    synchronized void search() {
        if (filter == 0) {
            updateGUI(methods.combinedAllMovies());
        } else if (filter == 1) {
            updateGUI(methods.mostRecentMovies());
        } else if (filter == 2) {
            updateGUI(methods.maxRevenueMovies());
        } else if (filter == 3) {
            updateGUI(methods.maxProfitMovies());
        } else if (filter == 4) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    long totalProfit = methods.totalProfit();
                    if (dialog.isShowing()) {
                        totalProfitDialogController.init(methods.getProductionCompany().getName(), totalProfit);
                        return;
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("fxml-total-profit-dialog.fxml"));
                    try {
                        DialogPane dialogPane = fxmlLoader.load();
                        totalProfitDialogController = fxmlLoader.getController();
                        totalProfitDialogController.init(methods.getProductionCompany().getName(), totalProfit);
                        dialog = new Dialog();
                        dialog.setDialogPane(dialogPane);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        dialog.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (filter == 5) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("fxml-movie-details-dialog.fxml"));
                    try {
                        DialogPane dialogPane = fxmlLoader.load();
                        movieDetailsController = fxmlLoader.getController();
                        String[] str = movie.getTitle().split(" ");
                        String posterName = "";
                        for (String s : str) {
                            String[] str_s = s.split(":");
                            for (String sw : str_s) {
                                posterName = posterName + sw;
                            }
                        }
                        posterName = posterName + "poster.jpg";
                        System.out.println(posterName);
                        Image poster;
                        try {
                            System.out.println(posterName);
                            poster = new Image(getClass().getResourceAsStream(posterName));
                        } catch (Exception e) {
                            e.printStackTrace();
                            poster = new Image(getClass().getResourceAsStream("Default.jpeg"));
                        }
                        movieDetailsController.init(movie, poster);
                        dialog = new Dialog();
                        dialog.setDialogPane(dialogPane);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        dialog.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (filter == 6) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("fxml-transfer-movie-dialog.fxml"));
                    try {
                        DialogPane dialogPane = fxmlLoader.load();
                        transferMovieController = fxmlLoader.getController();
                        transferMovieController.init(movie);
                        dialog = new Dialog();
                        dialog.setDialogPane(dialogPane);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        dialog.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (filter == 7) {
            if (string.strip().isEmpty()) return;
            updateGUI(methods.searchMovieByTitle(string));
        } else if (filter == 10) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("fxml-add-movie-dialog-box.fxml"));
                    try {
//                        System.out.println("Here");
                        DialogPane dialogPane = fxmlLoader.load();
                        addMovieDialogBoxController = fxmlLoader.getController();
                        addMovieDialogBoxController.init(methods.getProductionCompany());
                        dialog = new Dialog();
                        dialog.setDialogPane(dialogPane);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        dialog.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void updateGUI(int i) {
        setList(i);
        filter = 0;
        new Thread(this::search).start();
    }

    public void updateGUI(Movie movie, int filter) {
        this.filter = filter;
        this.movie = movie;
        new Thread(this::search).start();
    }

    public void updateGUI(int filter, String name) {
        this.filter = filter;
        this.string = name;
        new Thread(this::search).start();
    }

    public synchronized void updateGUI(List<Movie> movies) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GridPane grid = homepageController.getGrid();
                Label notFoundLabel = homepageController.getNotFoundLabel();
                grid.getChildren().clear();
                if (movies.isEmpty()) {
                    notFoundLabel.setVisible(true);
                    return;
                }
                notFoundLabel.setVisible(false);
                String address = "fxml-movie-box.fxml";
                for (int i = 0; i < movies.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(address));
                    try {
                        AnchorPane anchorPane = fxmlLoader.load();
                        grid.add(anchorPane, 0, i + 1);
                        GridPane.setMargin(anchorPane, new Insets(10));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MovieBoxController movieBoxController = fxmlLoader.getController();
                    movieBoxController.init(movies.get(i));
                }
            }
        });
    }

    public Updater(HomepageController homepageController) {
        this.homepageController = homepageController;
        this.methods = Methods.getMethods();
        productionCompany = methods.getProductionCompany();
    }

    public void refreshGUI(int refresherId) {
        new Thread(this::search).start();
    }

}
