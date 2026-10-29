import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void movieAttributeTest() {
        //Arrange / Act
        Movie movie = new Movie("Jaws", "SP", 1975, true, 124, "Thriller");

        //Assert
        assertEquals("Jaws", movie.getTitle());
        assertEquals("SP", movie.getDirector());
        assertEquals(1975, movie.getYearCreated());
        assertEquals(true, movie.isInColor());
        assertEquals(124, movie.getLengthInMinutes());
        assertEquals("Thriller", movie.getGenre());
    }

    @Test
    void setTitleChangeTest() {
        //Arrange
        Movie movie = new Movie("Jaws", "SP", 1975, true, 124, "Thriller");

        //Act
        movie.setTitle("Dødens gab");

        //Assert
        assertEquals("Dødens gab", movie.getTitle());
    }

    @Test
    void daysSinceWatchedCountsAcrossMonths() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        movie.markAsWatched(LocalDate.of(2025, 9, 30));

        // Act
        long days = movie.getDaysSinceWatched(LocalDate.of(2025, 10, 26));

        // Assert
        assertEquals(26, days);
    }

    @Test
    void newMovieIsNotWatched(){
        //Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        //Assert
        assertFalse(movie.hasBeenWatched());
        assertNull(movie.getLastWatched());

    }

    @Test
    void rememberDateTest(){
        //Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        LocalDate date = LocalDate.of(2026, 10, 26);

        //Act
        movie.markAsWatched(date);

        //Assert
        assertEquals(date, movie.getLastWatched());
    }

    @Test
    void emptyTitleThrowsException(){

        assertThrows(IllegalArgumentException.class,
                () -> new Movie("", "hej", 2000, true, 120, "Gyser"));
    }

    @Test
    void yearBefore1888IsRejected() {

        Movie movie = new Movie("Jaws", "Steven Spielberg", 1888, true, 124, "Thriller");

        assertThrows(IllegalArgumentException.class,
                () -> new Movie("Jaws", "Steven Spielberg", 1887, true, 124, "Thriller"));

        assertEquals(1888, movie.getYearCreated());
    }

    @Test
    void yearAtLeastTwoYearsInFutureRejected(){

        assertThrows(IllegalArgumentException.class,
                () -> new Movie("Jaws", "Steven Spielberg", 2028, true, 124, "Thriller"));

    }

    @Test
    void lengthZeroMinutesRejected(){

        assertThrows(IllegalArgumentException.class,
                () -> new Movie("Jaws", "Steven Spielberg", 1888, true, 0, "Thriller"));
    }

    @Test
    void invalidSetterValueLeavesValueUnchanged(){
        // Arrange
        Movie movie = new Movie("Jaws", "SP", 1975, true, 124, "Thriller");

        // Act + Assert: ugyldig værdi afvises
        assertThrows(IllegalArgumentException.class,
                () -> movie.setYearCreated(0));

        // Assert: den tidligere værdi er bevaret
        assertEquals(1975, movie.getYearCreated());
    }

    @Test
    void markAsWatchedInFutureIsRejected(){

        Movie movie = new Movie("Jaws", "SP", 1975, true, 124, "Thriller");

        assertThrows(IllegalArgumentException.class,
                () -> movie.markAsWatched(LocalDate.now().plusDays(1)));

    }
}