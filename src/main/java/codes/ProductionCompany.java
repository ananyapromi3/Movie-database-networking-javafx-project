package codes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductionCompany implements Serializable {
    String name;
    int movieCount;
    List<Movie> movieList = new ArrayList<>();

    String password;
    int id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductionCompany() {
    }

    public ProductionCompany(String productionCompanyName) {
        this.name = productionCompanyName;
    }

    public ProductionCompany(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getMovieCount() {
        return this.movieCount;
    }

    public void showProductionCompany() {
        if (!this.getName().equals("")) {
            System.out.println(
                    "Production Company: " + this.getName() + "    Total Movies: " + this.getMovieCount());
        }
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
        movie.setProductionCompany(this);
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void removeMovie(Movie movie) {
        movieList.remove(movie);
    }
}
