import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void searchMovies() {
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
    void deleteMovie() {
    }

    @Test
    void deleteMovie2() {
    }
}