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
}