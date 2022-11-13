package codes;

import controllers.HomepageController;
import network.LoginResponseObject;
import network.TransferRequestObject;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    ProductionCompany productionCompany;
    List<Movie> companyMovies = new ArrayList();
    List<Movie> movies = new ArrayList();
    List<Movie> movieListTemp = new ArrayList<>();
    HomepageController homepageController;
    private static Methods methods;

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }

    public List<Movie> getCompanyMovies() {
        return companyMovies;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void setCompanyMovies(List<Movie> companyMovies) {
        this.companyMovies = companyMovies;
    }

    public List<Movie> getAllMovies() {
        List<Movie> result = getMovies();
        for (Movie m : movieListTemp) {
            if (m.getProductionCompany().getName().equalsIgnoreCase(this.productionCompany.getName())) {
                result.add(m);
                removeFromTempList(m);
            }
        }
        return result;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovieListTemp() {
        return movieListTemp;
    }

    public void setMovieListTemp(List<Movie> movieListTemp) {
        this.movieListTemp = movieListTemp;
    }

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    private Methods() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Methods getMethods() {
        if (methods == null) {
            methods = new Methods();
        }
        return methods;
    }

    public static Methods getInstance(LoginResponseObject loginResponseObject) {
        if (methods == null) {
            methods = new Methods();
        }
        methods.productionCompany = loginResponseObject.getProductionCompany();
        methods.companyMovies = loginResponseObject.getProductionCompany().getMovieList();
        methods.movieListTemp = loginResponseObject.getMovieListTemp();
        return methods;
    }

    public static Methods getInstance(TransferRequestObject transferRequestObject) {
        if (methods == null) {
            methods = new Methods();
        }
        return methods;
    }

    public void setListToShow(int i) {
        if (i == 1) {
            movies = companyMovies;
        } else movies = movieListTemp;
    }

    public List<Movie> searchMovieByTitle(String movieTitle) {
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < combinedAllMovies().size(); i++) {
            Movie movie = combinedAllMovies().get(i);
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> searchByReleaseYear(int year) {
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getYearOfRelease() == year) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> searchByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            for (int j = 0; j < 3; j++) {
                if (movie.getGenre()[j].toLowerCase().equals(genre.toLowerCase())) {
                    result.add(movie);
                }
            }
        }
        return result;
    }

    public List<Movie> maxRevenueMovies() {
        int maxRevenue = 0;
        List<Movie> result = new ArrayList();
        for (Movie m : combinedAllMovies()) {
            if (m.getRevenue() > maxRevenue) maxRevenue = m.getRevenue();
        }
        for (Movie m : combinedAllMovies()) {
            if (m.getRevenue() == maxRevenue) result.add(m);
        }
        return result;
    }

    public List<Movie> combinedAllMovies() {
        for (Movie m : movieListTemp) System.out.println(m.getTitle() + " " + m.getProductionCompany().getName());
//        System.out.println("action...");
        List<Movie> result = new ArrayList();
        for (Movie m : movies) {
            result.add(m);
        }
        for (Movie m : movieListTemp) {
            if (m.getProductionCompany().getName().equalsIgnoreCase(productionCompany.getName())) {
                result.add(m);
            }
        }
//        for (Movie m : movieListTemp) System.out.println(m.getTitle() + " " + m.getProductionCompany().getName());
//        this.movies = result;
        return result;
    }

    public List<Movie> maxBudgetMovies() {
        int maxBudget = 0;
        List<Movie> result = new ArrayList();
        for (Movie m : movies) {
            if (m.getBudget() > maxBudget) maxBudget = m.getBudget();
        }
        for (Movie m : movies) {
            if (m.getBudget() == maxBudget) result.add(m);
        }
        return result;
    }

    public List<Movie> maxProfitMovies() {
        int maxProfit = 0;
        List<Movie> result = new ArrayList();
        for (Movie m : combinedAllMovies()) {
            if ((m.getRevenue() - m.getBudget()) > maxProfit) maxProfit = (m.getRevenue() - m.getBudget());
        }
        for (Movie m : combinedAllMovies()) {
            if ((m.getRevenue() - m.getBudget()) == maxProfit) result.add(m);
        }
        return result;
    }

    public List<Movie> mostRecentMovies() {
        int recentYear = 0;
        List<Movie> result = new ArrayList();
        for (Movie m : combinedAllMovies()) {
            if (m.getYearOfRelease() > recentYear) recentYear = m.getYearOfRelease();
        }
        for (Movie m : combinedAllMovies()) {
            if (m.getYearOfRelease() == recentYear) result.add(m);
        }
        return result;
    }

    public List<Movie> searchByRunTimeRange(int runTime_start, int runTime_end) {
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getRunningTime() <= runTime_end && movie.getRunningTime() >= runTime_start) {
                result.add(movie);
            }
        }
        return result;
    }

    public long totalProfit() {
        long temp_profit = 0;
        for (Movie m : combinedAllMovies()) temp_profit += (m.getRevenue() - m.getBudget());
        return temp_profit;
    }

    public void addToList(Movie movie) {
        companyMovies.add(movie);
    }

    public void addToTempMovieList(Movie movie) {
        removeFromProductionCompany(movie);
        movieListTemp.add(movie);
    }

    public List<Movie> removeFromProductionCompany(Movie movie) {
        for (int i = 0; i < companyMovies.size(); i++) {
            if (companyMovies.get(i).getId() == movie.getId()) {
                companyMovies.remove(i);
                break;
            }
        }
        return this.companyMovies;
    }

    public List<Movie> addMovie(Movie movie) {
        movies.add(movie);
        return this.movies;
    }

    public void removeFromTempList(Movie movie) {
        for (int i = 0; i < movieListTemp.size(); i++) {
            if (movieListTemp.get(i).getId() == movie.getId() || movieListTemp.get(i).getTitle().equalsIgnoreCase(movie.getTitle())) {
                movieListTemp.remove(i);
                break;
            }
        }
    }

}
