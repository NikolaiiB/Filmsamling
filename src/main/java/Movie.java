import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private LocalDate lastWatched;

    public Movie(String title, String director, int yearCreated, boolean inColor,
                 int lengthInMinutes, String genre) {
        setTitle(title);
        setDirector(director);
        setYearCreated(yearCreated);
        setInColor(inColor);
        setLengthInMinutes(lengthInMinutes);
        setGenre(genre);
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean isInColor() {
        return inColor;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {

        if(title.isBlank()){
            throw new IllegalArgumentException("Titlen må ikke være tom");
        }
        this.title = title;
    }

    public void setDirector(String director) {
        if(director.isBlank()){
            throw new IllegalArgumentException("Instruktør skal angives");
        }
        this.director = director;
    }

    public void setYearCreated(int yearCreated) {
        int latestYear = LocalDate.now().getYear() + 1;
        if(yearCreated < 1888 || yearCreated > latestYear){
            throw new IllegalArgumentException("Årstallet skal være mellem 1888 og " + latestYear);
        }
        this.yearCreated = yearCreated;
    }

    public void setInColor(boolean inColor) {
        this.inColor = inColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {

        if(lengthInMinutes < 1){
            throw new IllegalArgumentException("Filmens længde kan ikke under 1 minut");
        }
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setGenre(String genre) {
        if(genre.isBlank()){
            throw new IllegalArgumentException("Genren må ikke være tom");
        }
        this.genre = genre;
    }

    public LocalDate getLastWatched() {
        return lastWatched;
    }

    public boolean hasBeenWatched(){
        return lastWatched != null;
    }

    public void markAsWatched(LocalDate date){

        if(date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Sidst set kan ikke være i fremtiden");
        }
        lastWatched = date;
    }

    public long getDaysSinceWatched(LocalDate today){
        return ChronoUnit.DAYS.between(lastWatched, today);
    }
}
