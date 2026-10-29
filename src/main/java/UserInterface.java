import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
        private Scanner scanner;
        private Controller controller;

        public UserInterface() {
            scanner = new Scanner(System.in);
            controller = new Controller();
        }

        public void startProgram() {
            System.out.println("Velkommen til min filmsamling");
            String choice = "";
            while (!choice.equalsIgnoreCase("0")) {
                System.out.println();
                System.out.println("1. Opret en film");
                System.out.println("2. Vis alle film");
                System.out.println("3. Søg efter film");
                System.out.println("4. Rediger en film");
                System.out.println("5. Slet en film");
                System.out.println("6. Marker en film som set i dag");
                System.out.println("7. Find film du ikke har set længe");
                System.out.println("0. Afslut");
                System.out.print("Vælg: ");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> addMovie();
                    case "2" -> showMovies(controller.getMovies());
                    case "3" -> searchMovie();
                    case "4" -> editMovie();
                    case "5" -> deleteMovie();
                    case "6" -> markAsWatched();
                    case "7" -> daysSinceWatched();
                    case "0" -> System.out.println("Program afsluttet");
                    default -> System.out.println("Ugyldig kommando");
                }


            }
        }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    private boolean readYesOrNo(String prompt){
        while(true){
            System.out.print(prompt);
            String answer = scanner.nextLine().trim();
            if(answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("j")){
                return true;
            }
            else if(answer.equalsIgnoreCase("nej") || answer.equalsIgnoreCase("n")){
                return false;
            }
                System.out.println("Skriv ja, j eller nej, n");
        }
    }
    public void addMovie(){
            System.out.print("Titel: ");
            String title = scanner.nextLine().trim();

            System.out.print("Instruktør: ");
            String author = scanner.nextLine().trim();

            int yearCreated = readInt("Årstal: ");

            boolean inColor = readYesOrNo("I farve? (ja/nej): ");

            int length = readInt("Længde i minutter: ");

            System.out.print("Genre: ");
            String genre = scanner.nextLine().trim();

            try{
                controller.addMovie(title, author, yearCreated, inColor, length, genre);
                System.out.println(title + " er tilføjet til samlingen.");
            }
            catch(IllegalArgumentException e){
                System.out.println("Filmen blev ikke oprettet. " + e.getMessage());
            }
    }

    public void showMovies(ArrayList<Movie> movies){
        if(movies.isEmpty()) {
            System.out.println("Filmsamlingen er tom");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for(Movie movie : movies) {
            System.out.println();
            System.out.println("Titel: " + movie.getTitle() + "(" + movie.getYearCreated() + ")");
            System.out.println("  Instruktør: " + movie.getDirector());
            System.out.println("  Længde: " + movie.getLengthInMinutes() + " minutter, " + (movie.isInColor() ? "Farver" : "Sort/Hvid"));
            System.out.println("  Genre: " + movie.getGenre());

            if(movie.hasBeenWatched()){
                System.out.println("  Sidst set: "
                + movie.getLastWatched().format(formatter)
                + " (for " + movie.getDaysSinceWatched(LocalDate.now())
                + " dage siden)");
            }
            else{
                System.out.println("  Sidst set: aldrig");
            }

            System.out.println();
        }
    }

    public void searchMovie(){
        System.out.print("Søg efter titel: ");
        String title = scanner.nextLine().trim();

        ArrayList<Movie> matches = controller.searchMovies(title);

        if(matches.isEmpty()){
            System.out.println("Ingen film matcher søgningen");
        }
        else{
            System.out.println(matches.size() + " film matcher '" + title + "'");
            showMovies(matches);
        }
    }

    public Movie selectMovie(){
        System.out.print("Søg efter titel: ");
        String title = scanner.nextLine().trim();
        ArrayList<Movie> matches = controller.searchMovies(title);

        if(matches.isEmpty()){
            System.out.println("Ingen film matcher søgningen");
            return null;
        }

        int count = 1;
        for(Movie movie : matches) {
            System.out.println(count + ". " + movie.getTitle() + "(" + movie.getYearCreated() + ")");
            count++;
        }
        int choice;
        Movie movie = null;

        while(true){
            choice = readInt("Vælg nummer: ");
            try{
                movie = matches.get(choice-1);
                return movie;
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Vælg et nummber på listen");
                System.out.println();
            }
        }
    }

    public void editMovie(){
         Movie movie = selectMovie();

         if(movie == null){
             return;
         }

        System.out.println("Tryk enter for at beholde nuværende værdi.");

        System.out.print("Titel " + "[" + movie.getTitle() + "]: ");
        String newTitle = scanner.nextLine().trim();

        System.out.print("Instruktør " + "[" + movie.getDirector() + "]: ");
        String author = scanner.nextLine().trim();

        int newYearCreated = readInt("Årstal " + "[" + movie.getYearCreated() + "]: ");

        boolean changeColor = false;
        if(movie.isInColor()){
            changeColor = readYesOrNo("Ændre til sort/hvid? [Ja, Nej]: ");
        }
        else{
            changeColor = readYesOrNo("Ændre til farver? [Ja, Nej]: ");
        }

        int newLength = readInt("Længde i minutter " + "[" + movie.getLengthInMinutes() + "]: ");

        System.out.print("Genre " + "[" + movie.getGenre() + "]: ");
        String genre = scanner.nextLine().trim();

        controller.editMovie(movie, newTitle, author, newYearCreated, changeColor, newLength, genre);
        System.out.println("Filmen er opdateret");

        System.out.println();
        System.out.println("Titel: " + movie.getTitle() + "(" + movie.getYearCreated() + ")");
        System.out.println("  Instruktør: " + movie.getDirector());
        System.out.println("  Længde: " + movie.getLengthInMinutes() + " minutter, " + (movie.isInColor() ? "Farver" : "Sort/Hvid"));
        System.out.println("  Genre: " + movie.getGenre());
        System.out.println();

    }

    public void deleteMovie(){
        Movie movie = selectMovie();

        if(movie == null){
            return;
        }

        boolean delete = readYesOrNo("Vil du slette " + movie.getTitle() + "? (ja/nej): ");
        if(delete){
            controller.deleteMovie(movie);
            System.out.println(movie.getTitle() + " er slettet");
        }
        else{
            System.out.println("Intet er slettet");
        }
    }

    public void markAsWatched (){
        Movie movie = selectMovie();

        if(movie == null){
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        controller.markAsWatched(movie);
        System.out.println(movie.getTitle() + " er markeret som set " + LocalDate.now().format(formatter));
    }

    public void daysSinceWatched (){
        int choice = readInt("Hvor mange dage siden mindst? ");
        scanner.nextLine();

        System.out.println("Film du ikke har set i " + choice + " dage eller aldrig set: ");
        ArrayList<Movie> result = controller.getMoviesNotWatchedForDays(choice);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for(Movie movie : result) {
            if (movie.hasBeenWatched()) {
                String date = movie.getLastWatched().format(formatter);
                long days = movie.getDaysSinceWatched(LocalDate.now());
                System.out.println("  " + movie.getTitle() + " - sidst set: " + date + " (for " + days + " dage siden)");
            } else {
                System.out.println("  " + movie.getTitle() + " - sidst set: aldrig");
            }
        }

    }

    private void loadMovies(){
            try {
                int count = controller.loadMovies();
                System.out.println(count + " film er indlæst.");

                int skipped = controller.getSkippedLineCount();
                if(skipped > 0){
                    System.out.println("Advarsel: " + skipped + " linje(r) i filen kunne ikke læses og er sprunget over.");
                }
            } catch (FileNotFoundException e){
                System.out.println("Der er ingen gemt filmsamling endnu - du starter med en tom samling.");
            }
    }



    }
