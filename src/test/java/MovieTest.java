import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void movieAttributeTest(){
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
}