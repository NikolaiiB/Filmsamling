import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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

}
