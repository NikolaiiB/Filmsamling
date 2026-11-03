package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreComparatorTest {


    private MovieCollection collection = new MovieCollection();

    @BeforeEach
    void setUp(){
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    @Test
    void genreComparatorSortsByGenreAlphabetically() {
        GenreComparator comparator = new GenreComparator();
        Movie casablanca = collection.searchMovies("Casablanca").get(0);
        Movie godzilla = collection.searchMovies("Godzilla").get(0);
        Movie theGodfather = collection.searchMovies("The Godfather").get(0);

        assertTrue(comparator.compare(theGodfather, casablanca) < 0);
        assertTrue(comparator.compare(godzilla, casablanca) > 0);
        assertTrue(comparator.compare(casablanca, casablanca) == 0);
    }

}