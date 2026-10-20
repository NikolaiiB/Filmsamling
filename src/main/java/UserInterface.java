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
                System.out.println("0. Afslut");
                System.out.print("Vælg: ");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> System.out.println("Du har tilføjet en film\n");
                    case "2" -> {
                        if(controller.getMovies().isEmpty()) {
                            System.out.println("Filmsamlingen er tom");
                        }
                        for(Movie movie : controller.getMovies()) {
                            System.out.println();
                            System.out.println("Titel: " + movie.getTitle());
                            System.out.println("Instruktør: " + movie.getDirector());
                            System.out.println("Årstal: " + movie.getYearCreated());
                            System.out.println("Længde i minutter: " + movie.getLengthInMinutes());
                            System.out.println("Genre: " + movie.getGenre());
                            System.out.println("I farver: " + (movie.isInColor() ? "Ja" : "Nej"));
                            System.out.println();
                        }
                    }
                    case "0" -> System.out.println("Program afsluttet");
                    default -> System.out.println("Ugyldig kommando");
                }
            }
        }




    }
