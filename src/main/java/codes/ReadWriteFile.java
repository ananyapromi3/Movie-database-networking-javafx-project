package codes;

import network.AddMovieObject;
import network.AddMovieRequestObject;
import network.TransferRequestObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadWriteFile {
    private static ReadWriteFile instance;

    HashMap<String, ProductionCompany> productionCompanyHashMap = new HashMap<>();
    public List<Movie> movies = new ArrayList();
    public List<ProductionCompany> productionCompanies = new ArrayList<>();
    List<Movie> movieListTemp = new ArrayList<>();

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";
    private static final String INPUT_PASSWORD_FILE_NAME = "password.txt";
    private static final String OUTPUT_PASSWORD_FILE_NAME = "password.txt";

    public HashMap<String, ProductionCompany> getProductionCompanyHashMap() {
        return productionCompanyHashMap;
    }

    public void setProductionCompanyHashMap(HashMap<String, ProductionCompany> productionCompanyHashMap) {
        this.productionCompanyHashMap = productionCompanyHashMap;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Movie> getMovieListTemp() {
        return movieListTemp;
    }

    public void setMovieListTemp(List<Movie> movieListTemp) {
        this.movieListTemp = movieListTemp;
    }

    private ReadWriteFile() {
        try {
//            System.out.println("Reading files...");
            readFromInputFile();
            readPasswordsFromFile();
//            System.out.println("Reading files done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReadWriteFile getInstance() {
        if (instance == null) {
            instance = new ReadWriteFile();
        }
        return instance;
    }

    public void addMovie(Movie m) {
        m.setId(movies.size());
        movies.add(m);
    }

    public ProductionCompany addProductionCompany(String productionCompanyName) {
        if (productionCompanyHashMap.containsKey(productionCompanyName)) {
            return productionCompanyHashMap.get(productionCompanyName);
        }
        ProductionCompany productionCompany = new ProductionCompany(productionCompanyName);
        productionCompanyHashMap.put(productionCompanyName, productionCompany);
        productionCompany.setId(productionCompanies.size());
        productionCompanies.add(productionCompany);
        return productionCompany;
    }

    public void readFromInputFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] tokens = line.split(",");
            addMovieFromLine(line);
        }
        br.close();
    }

    public void addMovieFromLine(String line) {
        Movie movie = new Movie();
        String[] parts = line.split(",");
        int i = 0;
        movie.setTitle(parts[i++]);
        movie.setYearOfRelease(Integer.parseInt(parts[i++]));
        int j = 0;
        while (j < 3) {
            movie.genre[j++] = parts[i++];
        }
        movie.runningTime = Integer.parseInt(parts[i++]);
        ProductionCompany productionCompany = addProductionCompany(parts[i++]);
        movie.productionCompany = productionCompany;
        movie.budget = Integer.parseInt(parts[i++]);
        movie.revenue = Integer.parseInt(parts[i]);
        addMovie(movie);
        if (!movie.isBeingTransferred())
            productionCompany.addMovie(movie);
        else movieListTemp.add(movie);
    }

    public void readPasswordsFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_PASSWORD_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            ProductionCompany productionCompany = addProductionCompany(tokens[0]);
            productionCompany.setPassword(tokens[1]);
        }
        br.close();
    }

    public void writeToInputFile() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (ProductionCompany productionCompany : productionCompanies) {
            for (Movie movie : productionCompany.getMovieList()) {
                bw.write(getMovieLine(movie));
                bw.write(System.lineSeparator());
            }
        }
        bw.close();
    }

    public String getMovieLine(Movie movie) {
        String movieText = new String(movie.getTitle() + "," + movie.getYearOfRelease() + "," + movie.getGenre()[0] + ","
                + movie.getGenre()[1] + "," + movie.getGenre()[2] + "," + movie.getRunningTime() + "," + movie.getProductionCompany().getName() + ","
                + movie.getBudget() + "," + movie.getRevenue());
        return movieText;
    }

    public void writePasswordToInputFile() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PASSWORD_FILE_NAME));
        for (ProductionCompany productionCompany : productionCompanies) {
            bw.write(productionCompany.getName() + "," + productionCompany.getPassword());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }

    public ProductionCompany checkProductionCompany(String productionCompany) {
        return productionCompanyHashMap.get(productionCompany);
    }

    public ProductionCompany signUpProductionCompany(String productionCompanyName, String password) {
        for (ProductionCompany productionCompany : productionCompanies) {
            if (productionCompany.getName().equals(productionCompanyName)) {
                return null;
            }
        }
        ProductionCompany productionCompany = new ProductionCompany(productionCompanyName, password);
        productionCompanyHashMap.put(productionCompanyName, productionCompany);
        productionCompany.setId(productionCompanies.size());
        productionCompanies.add(productionCompany);
        return productionCompany;
    }

    public void addMovieToProductionCompany(int pCompanyId, Movie movie) {
        ProductionCompany productionCompany = new ProductionCompany();
        for (int i = 0; i < productionCompanies.size(); i++) {
            if (productionCompanies.get(i).getId() == pCompanyId) {
                movie.setProductionCompany(productionCompany);
                productionCompanies.get(i).addMovie(movie);
                break;
            }
        }
    }

    public synchronized Movie transferFrom(TransferRequestObject transferRequestObject) {
        Movie movie = movies.get(transferRequestObject.getMovieId());
        if (movie.isBeingTransferred() || movie.getProductionCompany().getId() != transferRequestObject.getTransferFromCompanyId())
            return null;
        movie.setBeingTransferred(true);
        productionCompanies.get(transferRequestObject.getTransferFromCompanyId()).removeMovie(movie);
        movieListTemp.add(movie);
        return movie;
    }

    public synchronized Movie receive(AddMovieRequestObject addMovieRequestObject) {
        Movie movie = movies.get(addMovieRequestObject.getMovieId());
        if (!movie.isBeingTransferred()) return null;
        movie.setBeingTransferred(false);
        productionCompanies.get(addMovieRequestObject.getReceiverCompany().getId()).addMovie(movie);
        movieListTemp.remove(movie);
        return movie;
    }

    public synchronized Movie add(AddMovieObject addMovieObject) {
        Movie movie = addMovieObject.getMovie();
        if (!movie.isBeingTransferred()) return null;
        movie.setBeingTransferred(false);
        productionCompanies.get(addMovieObject.getProductionCompany().getId()).addMovie(movie);
        return movie;
    }

}
