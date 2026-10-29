import java.util.ArrayList;
import java.time.LocalDate;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor,
                         int lengthInMinutes, String genre) {

        Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        movies.add(movie);
        // TODO US1
    }

    public ArrayList<Movie> getMovies() {
        return movies;   // TODO US3
    }

    public int getNumberOfMovies() {
        return movies.size();      // TODO
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movies){
          if ( movie.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
              matchingMovies.add(movie);
          }
        }
        return matchingMovies;
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated,
                             boolean changeColor, int lengthInMinutes, String genre)
    {
        if (!movies.contains(movie))
        {
            return false;  // TODO US5
        }

        new Movie(title, director, yearCreated, changeColor, lengthInMinutes, genre);

        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        if (changeColor)
        {
            if(movie.isInColor()){
                movie.setInColor(false);
            }
            else{
                movie.setInColor(true);
            }
        }
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);
        return true;
    }

    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);  // TODO US6
    }

    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date){
        ArrayList<Movie> result = new ArrayList<>();

        for(Movie movie : movies){
            if (!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)) {
                result.add(movie);
            }
        }
        return result;
    }
}

