package network;

import codes.Movie;
import codes.ProductionCompany;

import java.io.Serializable;

public class AddMovieObject implements Serializable {
    Movie movie;
    ProductionCompany productionCompany;

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public AddMovieObject(Movie movie, ProductionCompany productionCompany) {
        this.movie = movie;
        this.productionCompany = productionCompany;
    }
}
