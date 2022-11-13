package network;

import codes.ProductionCompany;

import java.io.Serializable;

public class TransferRequestObject implements Serializable {
    int movieId;
    int transferFromCompanyId;
    int transferToCompanyId;
    ProductionCompany transferToProductionCompany;

    public int getTransferToCompanyId() {
        return transferToCompanyId;
    }

    public void setTransferToCompanyId(int transferToCompanyId) {
        this.transferToCompanyId = transferToCompanyId;
    }

    public int getMovieId() {
        return movieId;
    }

    public TransferRequestObject(int movieId, int transferFromCompanyId, ProductionCompany transferToProductionCompany) {
        this.movieId = movieId;
        this.transferFromCompanyId = transferFromCompanyId;
        this.transferToProductionCompany = transferToProductionCompany;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTransferFromCompanyId() {
        return transferFromCompanyId;
    }

    public void setTransferFromCompanyId(int transferFromCompanyId) {
        this.transferFromCompanyId = transferFromCompanyId;
    }
}
