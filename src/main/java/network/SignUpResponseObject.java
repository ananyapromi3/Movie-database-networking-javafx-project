package network;

import codes.Movie;
import codes.ProductionCompany;

import java.io.Serializable;
import java.util.List;

public class SignUpResponseObject extends LoginResponseObject implements Serializable {
    public SignUpResponseObject(boolean access, ProductionCompany productionCompany, List<Movie> marketmovies) {
        super(access, productionCompany, marketmovies);
    }
}
