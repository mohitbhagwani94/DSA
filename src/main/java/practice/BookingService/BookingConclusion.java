package practice.BookingService;

public class BookingConclusion {
    String status;
    int auditoriumNumber;

    public BookingConclusion(String status, int auditoriumNumber) {
        this.status = status;
        this.auditoriumNumber = auditoriumNumber;
    }
    @Override
    public String toString() {
        if (status.equalsIgnoreCase("successful")) {
            return status + " " + auditoriumNumber;
        }
        return "false";
    }
}
