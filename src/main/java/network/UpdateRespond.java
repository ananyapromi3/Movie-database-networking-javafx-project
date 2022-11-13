package network;

import codes.Movie;
import codes.ProductionCompany;

import java.io.Serializable;

public class UpdateRespond implements Serializable {

    Movie movie;
    int transferOrReceive;
    int transferCompanyID;
    int receiverCompanyID;
    ProductionCompany receiverProductionCompany;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getTransferOrReceive() {
        return transferOrReceive;
    }

    public void setTransferOrReceive(int transferOrReceive) {
        this.transferOrReceive = transferOrReceive;
    }

    public int getTransferCompanyID() {
        return transferCompanyID;
    }

    public void setTransferCompanyID(int transferCompanyID) {
        this.transferCompanyID = transferCompanyID;
    }

    public int getReceiverCompanyID() {
        return receiverCompanyID;
    }

    public void setReceiverCompanyID(int receiverCompanyID) {
        this.receiverCompanyID = receiverCompanyID;
    }

    public ProductionCompany getReceiverProductionCompany() {
        return receiverProductionCompany;
    }

    public void setReceiverProductionCompany(ProductionCompany receiverProductionCompany) {
        this.receiverProductionCompany = receiverProductionCompany;
    }

    public UpdateRespond(Movie movie, int transferOrReceive, int transferCompanyID, ProductionCompany receiverProductionCompany) {
        this.movie = new Movie(movie);
        this.transferOrReceive = transferOrReceive;
        this.transferCompanyID = transferCompanyID;
        this.receiverProductionCompany = receiverProductionCompany;
    }
}
