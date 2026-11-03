package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleComparatorTest {
    private MovieCollection collection;

    private Movie theGodfather;
    private Movie casablanca;
    private Movie godzilla;

    @BeforeEach
    void setUp(){
        collection = new MovieCollection();

        theGodfather = new Movie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        casablanca = new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        godzilla = new Movie("Godzilla", "Ishirō Honda", 1954, false, 150, "Sci-fi");

        collection.addMovie(theGodfather);
        collection.addMovie(casablanca);
        collection.addMovie(godzilla);
    }

    @Test
    void titleComparatorSortsByTitleAlphabetically() {
        TitleComparator comparator = new TitleComparator();

        assertTrue(comparator.compare(casablanca, godzilla) < 0);
        assertTrue(comparator.compare(theGodfather, godzilla) > 0);
        assertTrue(comparator.compare(casablanca, casablanca) == 0);
    }

    @Test
    void titleComparatorIgnoresUpperLowerCase(){
        TitleComparator comparator = new TitleComparator();

        //To lowercase
        casablanca.setTitle("casablanca");

        assertTrue(comparator.compare(casablanca, godzilla) < 0);
    }
}