package network;

import codes.ReadWriteFile;
import codes.Movie;
import codes.ProductionCompany;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteThreadServer {
    private List<ClientInfo> clientList = new ArrayList<>();

    public synchronized void login(LoginRequestObject request, NetworkUtil networkUtil) {
        ProductionCompany productionCompany = ReadWriteFile.getInstance().checkProductionCompany(request.getProductionCompanyName());
        try {
            if (productionCompany == null || !productionCompany.getPassword().equals(request.getPassword())) {
                networkUtil.write(new LoginResponseObject(false, null, new ArrayList<Movie>()));
            } else {
                LoginResponseObject loginResponseObject = new LoginResponseObject(true, productionCompany, ReadWriteFile.getInstance().getMovieListTemp());
                networkUtil.write(loginResponseObject);
                clientList.add(new ClientInfo(networkUtil, productionCompany.getId()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void signUp(SignUpRequestObject signUpRequestObject, NetworkUtil networkUtil) {
        ProductionCompany productionCompany = ReadWriteFile.getInstance().signUpProductionCompany(signUpRequestObject.getProductionCompanyName(), signUpRequestObject.getPassword());
        try {
            if (productionCompany == null || !productionCompany.getPassword().equals(signUpRequestObject.getPassword())) {
                networkUtil.write(new SignUpResponseObject(false, null, new ArrayList<Movie>()));
            } else {
                LoginResponseObject loginResponseObject = new SignUpResponseObject(true, productionCompany, ReadWriteFile.getInstance().getMovieListTemp());
                networkUtil.write(loginResponseObject);
                clientList.add(new ClientInfo(networkUtil, productionCompany.getId()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void transfer(TransferRequestObject transferRequestObject) {
        Movie movie = ReadWriteFile.getInstance().transferFrom(transferRequestObject);
//        Movie movie = transferRequestObject.getMovieId();
        if (movie == null) {
            System.out.println("Movie null in WriteThreadServer transfer");
            return;
        }
        UpdateRespond updateRespond = new UpdateRespond(movie, 1, movie.getProductionCompany().getId(), transferRequestObject.transferToProductionCompany);
        for (ClientInfo client : clientList) {
            client.write(updateRespond);
        }
    }

    public synchronized void receive(AddMovieRequestObject addMovieRequestObject) {
        Movie movie = ReadWriteFile.getInstance().receive(addMovieRequestObject);
        if (movie == null) {
            System.out.println("Movie null in WriteThreadServer receive");
            return;
        }
        UpdateRespond updateRespond = new UpdateRespond(movie, 2, movie.getProductionCompany().getId(), addMovieRequestObject.receiverCompany);
        for (ClientInfo client : clientList) {
            client.write(updateRespond);
        }
    }

    public synchronized void addMovie(AddMovieObject addMovieObject) {
        Movie movie = ReadWriteFile.getInstance().add(addMovieObject);
//        Movie movie = addMovieObject.getMovie();
        if (movie == null) {
            System.out.println("Movie null in WriteThreadServer addMovie");
            return;
        }
//        System.out.println("In addMovie, WriteThreadServer: " + addMovieObject.getMovie().getTitle() + " " + addMovieObject.getProductionCompany().getName());
        UpdateRespond updateRespond = new UpdateRespond(movie, 3, -1, addMovieObject.getProductionCompany());
        for (ClientInfo client : clientList) {
            client.write(updateRespond);
        }
    }

    public synchronized void logout(NetworkUtil networkUtil) {
        clientList.removeIf(client -> client.getNetworkUtil() == networkUtil);
    }
}
