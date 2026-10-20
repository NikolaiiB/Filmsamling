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
            String choice = "";
            while (!choice.equalsIgnoreCase("0")) {
                System.out.println();
                System.out.println("1. Opret en film");
                System.out.println("2. Vis alle film");
                System.out.println("3. Søg efter film");
                System.out.println("0. Afslut");
                System.out.print("Vælg: ");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> addMovie();
                    case "2" -> showMovies(controller.getMovies());
                    case "3" -> searchMovie();
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
            System.out.println("Film tilføjet");
    }

    public void showMovies(ArrayList<Movie> movies){
        if(movies.isEmpty()) {
            System.out.println("Filmsamlingen er tom");
        }
        for(Movie movie : movies) {
            System.out.println();
            System.out.println("Titel: " + movie.getTitle() + "(" + movie.getYearCreated() + ")");
            System.out.println("  Instruktør: " + movie.getDirector());
            System.out.println("  Længde: " + movie.getLengthInMinutes() + " minutter," + (movie.isInColor() ? "Farver" : "Sort/Hvid"));
            System.out.println("  Genre: " + movie.getGenre());
            System.out.println();
        }
    }

    public void searchMovie(){
        System.out.print("Søg efter titel: ");
        String title = scanner.nextLine().trim();

        System.out.println(controller.searchMovies(title).size() + " film matcher " + "'" + title + "'");

        showMovies(controller.searchMovies(title));
    }


    }
