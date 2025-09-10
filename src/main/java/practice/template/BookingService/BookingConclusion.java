package practice.template.BookingService;

public class BookingConclusion {
    String successful;
    int auditoriumNumber;

    public BookingConclusion(String successful, int auditoriumNumber) {
        this.successful = successful;
        this.auditoriumNumber = auditoriumNumber;
    }
    @Override
    public String toString() {
        if (status.equals("successful")) {
            return status + " " + auditoriumNumber;
        }
        return "false";
    }
}
