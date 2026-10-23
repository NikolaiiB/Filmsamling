import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    private MovieCollection collection;

    @BeforeEach
    void setUp(){
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    @Test
    void getNumberOfMovies() {
        //Arrange
        MovieCollection collection2 = new MovieCollection();

        //Act
        int size = collection2.getNumberOfMovies();

        //Assert
        assertEquals(0, size);

    }

    @Test
    void addOneMovie() {
        //Arrange
        MovieCollection collection2 = new MovieCollection();
        collection2.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");

        //Act
        int size = collection2.getNumberOfMovies();

        //Assert
        assertEquals(1, size);

    }

    @Test
    void addMoreMovies() {
        //Act
        collection.addMovie("Lucky Luke", "Donald Trump", 1999, false, 20, "Horror");
        collection.addMovie("Guldbar", "Gulddreng", 2025, true, 432, "Comedy");

        int size = collection.getNumberOfMovies();

        //assert
        assertEquals(5, size);
    }

    @Test
    void searchWithSeveralMatches() {
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void searchMoviesWithoutMatch() {
        //Arrange
        ArrayList<Movie> matches;

        //Act
        matches = collection.searchMovies("Harry");

        //Assert
        assertEquals(0, matches.size());

    }

    @Test
    void searchMoviesOneMatch() {
        ArrayList<Movie> matches;

        //Act
        matches = collection.searchMovies("Casa");

        //Assert
        assertEquals(1, matches.size());
    }

    @Test
    void searchMoviesCaps() {
        ArrayList<Movie> matches;

        //Act
        matches = collection.searchMovies("CaSA");

        //Assert
        assertEquals(1, matches.size());

    }

    @Test
    void editMovie() {
        //Arrange
        collection.getMovies().get(0);
        Movie movie = collection.getMovies().get(0);

        //Act
        boolean edit = collection.editMovie(movie,"Harry", "assas", 2020, "yes",210,"Action");

        //Assert
        assertTrue(edit);

    }

    @Test
    void editMovie2() {
        //Arrange
        Movie movie = new Movie("Harry","asa",20202,true, 2021, "Action");

        //Act
        boolean edit = collection.editMovie(movie,"Harry", "assas", 2020, "yes",210,"Action");

        //Assert
        assertFalse(edit);
    }

    @Test
    void deleteMovieRemovesIt() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean deleted = collection.deleteMovie(casablanca);

        // Assert
        assertTrue(deleted);
        assertEquals(2, collection.getNumberOfMovies());
        assertTrue(collection.searchMovies("Casablanca").isEmpty());
    }

    @Test
    void deleteMovieNotInCollectionReturnsFalse() {
        Movie jaws = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        boolean deleted = collection.deleteMovie(jaws);

        assertFalse(deleted);
        assertEquals(3, collection.getNumberOfMovies());
    }
}