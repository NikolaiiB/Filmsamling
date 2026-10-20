import java.util.ArrayList;

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
        return 0;      // TODO
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movies){
          if ( movie.getTitle().contains(searchText)) {
              matchingMovies.add(movie);
          }
        }
        return matchingMovies;
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated,
                             String inColor, int lengthInMinutes, String genre)
    {
        if (!movies.contains(movie))
        {
            return false;  // TODO US5
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
        if (!inColor.isEmpty())
        {
            boolean color = false;
            if (inColor.equalsIgnoreCase("ja"))
            {
                color = true;
            }
            movie.setInColor(color);
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
        return false;  // TODO US6
    }

}

