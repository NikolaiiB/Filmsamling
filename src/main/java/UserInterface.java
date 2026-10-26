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

        public void addMovie(){
            System.out.print("Titel: ");
            String title = scanner.nextLine().trim();

            System.out.print("Instruktør: ");
            String author = scanner.nextLine().trim();

            System.out.print("Årstal: ");
            int yearCreated = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("I farve? (ja/nej): ");
            String answer = scanner.nextLine().trim();
            Boolean inColor = (answer.equalsIgnoreCase("ja"));

            System.out.print("Længde i minutter: ");
            int length = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Genre: ");
            String genre = scanner.nextLine().trim();

            controller.addMovie(title, author, yearCreated, inColor, length, genre);
            System.out.println(title + " er tilføjet til samlingen.");
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

        System.out.print("Vælg nummer: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Movie movie = matches.get(choice-1);

        return movie;
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

        System.out.print("Årstal " + "[" + movie.getYearCreated() + "]: ");
        String yearCreated = scanner.nextLine().trim();
        int newYearCreated = 0;
        if(!yearCreated.isEmpty()) {
            newYearCreated = Integer.parseInt(yearCreated);
        }
        System.out.print("I farve? (ja/nej) " + "[" + (movie.isInColor() ? "Ja" : "Nej") + "]: ");
        String inColor = scanner.nextLine().trim();

        System.out.print("Længde i minutter " + "[" + movie.getLengthInMinutes() + "]: ");
        String length = scanner.nextLine().trim();
        int newLength = 0;
        if(!length.isEmpty()) {
            newLength = Integer.parseInt(length);
        }

        System.out.print("Genre " + "[" + movie.getGenre() + "]: ");
        String genre = scanner.nextLine().trim();

        controller.editMovie(movie, newTitle, author, newYearCreated, inColor, newLength, genre);
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

        System.out.print("Vil du slette " + movie.getTitle() + "? (ja/nej): ");
        String deleteChoice = scanner.nextLine().toLowerCase().trim();
        if(deleteChoice.equals("ja")){
            controller.deleteMovie(movie);
            System.out.println(movie.getTitle() + " er slettet");
        }
        else if(deleteChoice.equals("nej")){
            System.out.println("Intet er slettet");
        }
        else{
            System.out.println("Ugyldigt input. Intet er slettet");
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
        System.out.print("Hvor mange dage siden mindst? ");
        int choice = scanner.nextInt();
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



    }
