package network;

import codes.Movie;
import codes.ProductionCompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginResponseObject implements Serializable {
    boolean access = false;
    ProductionCompany productionCompany = new ProductionCompany();
    List<Movie> MovieListTemp = new ArrayList<>();

    public void setAccess(boolean access) {
        this.access = access;
    }

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public List<Movie> getMovieListTemp() {
        return MovieListTemp;
    }

    public void setMovieListTemp(List<Movie> movieList) {
        this.MovieListTemp = movieList;
    }

    public boolean isAccess() {
        return this.access;
    }

    public LoginResponseObject(boolean access, ProductionCompany productionCompany, List<Movie> movieList) {
        this.access = access;
        this.productionCompany = productionCompany;
        this.MovieListTemp = movieList;
    }
}
