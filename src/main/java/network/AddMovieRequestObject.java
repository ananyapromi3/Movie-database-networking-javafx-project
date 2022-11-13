package network;

import codes.ProductionCompany;

import java.io.Serializable;

public class AddMovieRequestObject implements Serializable {
    int movieId;
    ProductionCompany receiverCompany;

    public int getMovieId() {
        return movieId;
    }

    public AddMovieRequestObject(int movieId, ProductionCompany receiverCompany) {
        this.movieId = movieId;
        this.receiverCompany = receiverCompany;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public ProductionCompany getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(ProductionCompany receiverCompany) {
        this.receiverCompany = receiverCompany;
    }
}
