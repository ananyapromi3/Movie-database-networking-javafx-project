package codes;

import java.io.Serializable;

public class Movie implements Serializable {
    String title;
    int yearOfRelease;
    String[] genre = new String[3];
    int runningTime;
    int budget;
    int revenue;
    ProductionCompany productionCompany;
    boolean isBeingTransferred = false;
    int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTransferValue(boolean val) {
        this.isBeingTransferred = val;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getGenreStr() {
        String genre_str = "";
        int i = 1;
        for (String str : this.genre) {
            if (str.length() > 0 && i > 1) {
                genre_str = genre_str + ", " + str;
            } else if (str.length() > 0) {
                genre_str = genre_str + str;
            }
            i++;
        }
        return genre_str;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public boolean isBeingTransferred() {
        return isBeingTransferred;
    }

    public void setBeingTransferred(boolean beingTransferred) {
        isBeingTransferred = beingTransferred;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie(String title, int yearOfRelease, String[] genre, int runningTime, int budget, int revenue, ProductionCompany productionCompany) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
        this.runningTime = runningTime;
        this.budget = budget;
        this.revenue = revenue;
        this.productionCompany = productionCompany;
    }

    public Movie(Movie movie) {
        this.title = movie.title;
        this.yearOfRelease = movie.yearOfRelease;
        this.genre = movie.genre;
        this.runningTime = movie.runningTime;
        this.productionCompany = movie.productionCompany;
        this.budget = movie.budget;
        this.revenue = movie.revenue;
        this.isBeingTransferred = movie.isBeingTransferred;
        this.id = movie.id;
    }

    public Movie() {
    }

}