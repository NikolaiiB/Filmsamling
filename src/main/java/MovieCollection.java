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
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public int getNumberOfMovies() {
        return movies.size();
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
            return false;
        }
        if (!title.isEmpty())
        {
            movie.setTitle(title);
        }
        if (!director.isEmpty())
        {
            movie.setDirector(director);
        }
        if (!(yearCreated == 0))
        {
            movie.setYearCreated(yearCreated);
        }
        if (changeColor)
        {
            if(movie.isInColor()){
                movie.setInColor(false);
            }
            else{
                movie.setInColor(true);
            }
        }
        if (!(lengthInMinutes == 0))
        {
            movie.setLengthInMinutes(lengthInMinutes);
        }
        if (!genre.isEmpty())
        {
            movie.setGenre(genre);
        }
        return true;
    }

    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
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

