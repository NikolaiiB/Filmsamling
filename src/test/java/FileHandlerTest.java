import datasource.FileHandler;
import domainmodel.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {

    private static final String TEST_FILE = "test-movies.csv";

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void brokenLinesAreSkipped() throws FileNotFoundException {
        PrintStream output = new PrintStream(TEST_FILE);
        output.println("title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched");
        output.println("Jaws;Steven Spielberg;1975;true;124;Thriller;");
        output.println("Jaws;Steven Spielberg;abc;true;124;Thriller;");
        output.println("ødelagt linje");
        output.close();

        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Movie> movies = fileHandler.loadMovies();
        assertEquals(1, movies.size());
        assertEquals(2, fileHandler.getSkippedLineCount());
    }

    @Test
    void emptyListCanBeSavedAndLoaded () throws FileNotFoundException{
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Movie> movies = new ArrayList<>();

        fileHandler.saveMovies(movies);

        ArrayList<Movie> loadedMovies = fileHandler.loadMovies();

        assertEquals(0, loadedMovies.size());
    }

    @Test
    void loadingMissingFileThrowsException(){
        new File(TEST_FILE).delete();
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        assertThrows(FileNotFoundException.class, () -> {
            fileHandler.loadMovies();
        });
    }

    @Test
    void savedMoviesCanBeLoadedAgain() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Movie> movies = new ArrayList<>();
        LocalDate date = LocalDate.now();

        Movie jaws = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        Movie adamsÆbler = new Movie("Adams æbler", "Anders Thomas Jensen", 2005, true, 94, "Komedie");

        adamsÆbler.markAsWatched(date);
        movies.add(jaws);
        movies.add(adamsÆbler);

        fileHandler.saveMovies(movies);
        ArrayList<Movie> loadedMovies = fileHandler.loadMovies();

        assertEquals(2,loadedMovies.size());

        Movie loadedJaws = loadedMovies.get(0);

        assertEquals("Jaws", loadedJaws.getTitle());
        assertEquals("Steven Spielberg", loadedJaws.getDirector());
        assertEquals(1975, loadedJaws.getYearCreated());
        assertEquals(true, loadedJaws.isInColor());
        assertEquals(124, loadedJaws.getLengthInMinutes());
        assertEquals("Thriller", loadedJaws.getGenre());
        assertEquals(null, loadedJaws.getLastWatched());

        Movie loadedAdams = loadedMovies.get(1);

        assertEquals("Adams æbler", loadedAdams.getTitle());
        assertEquals("Anders Thomas Jensen", loadedAdams.getDirector());
        assertEquals(2005, loadedAdams.getYearCreated());
        assertEquals(true, loadedAdams.isInColor());
        assertEquals(94, loadedAdams.getLengthInMinutes());
        assertEquals("Komedie", loadedAdams.getGenre());
        assertEquals(date, loadedAdams.getLastWatched());
    }

}
