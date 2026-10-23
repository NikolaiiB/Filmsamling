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
    }

    @Test
    void addMovie() {
    }

    @Test
    void addMovie2() {
    }

    @Test
    void searchWithSeveralMatches() {
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void searchMovies2() {
    }

    @Test
    void searchMovies3() {
    }

    @Test
    void searchMovies4() {
    }

    @Test
    void editMovie() {
    }

    @Test
    void editMovie2() {
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