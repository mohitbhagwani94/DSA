package BookingService;

public class BookingConclusion {
    String status;
    int auditoriumNumber;

    public BookingConclusion(String status, int auditoriumNumber) {
        this.status = status;
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
