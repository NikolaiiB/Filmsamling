import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection;
    private FileHandler fileHandler;
    private boolean changed;

    public Controller(){
        movieCollection = new MovieCollection();
        fileHandler = new FileHandler("movies.csv");
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor,
                         int lengthInMinutes, String genre){
        movieCollection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);

        changed = true;
    }

    public ArrayList<Movie> getMovies() {
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        return movieCollection.searchMovies(searchText);
    }

    public boolean editMovie(Movie movie, String title, String director,
                             int yearCreated, boolean changeColor,
                             int lengthInMinutes, String genre){
        boolean edited = movieCollection.editMovie(movie, title, director,
                yearCreated, changeColor, lengthInMinutes, genre);

        if(edited){
            changed = true;
        }

        return edited;
    }

    public boolean deleteMovie(Movie movie){
        boolean deleted = movieCollection.deleteMovie(movie);

        if(deleted){
            changed = true;
        }

        return deleted;
    }

    public void markAsWatched(Movie movie, LocalDate date){
        movie.markAsWatched(date);
        changed = true;
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days){
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }

    public boolean saveMoviesIfChanged() throws FileNotFoundException {
        if(!changed){
            return false;
        }
        fileHandler.saveMovies(movieCollection.getMovies());
        changed = false;
        return true;
    }

    public int loadMovies() throws FileNotFoundException {
        ArrayList<Movie> movies = fileHandler.loadMovies();

        for(Movie movie : movies){
            movieCollection.addMovie(movie);
        }

        return movies.size();
    }

    public int getSkippedLineCount(){
        return fileHandler.getSkippedLineCount();
    }

}
