package practice.BookingService;

public class ShowBookingTest {
    public static void main(String[] args) {
        IRepo repo = new Repo();
        BookingService service = new BookingService(repo);

        // Register events in auditoriums
        repo.registerEventInAuditorium(2, 4, 2);
        repo.registerEventInAuditorium(2, 5, 2);
        repo.registerEventInAuditorium(2, 7, 2);
        repo.registerEventInAuditorium(2, 1, 2);
        repo.registerEventInAuditorium(2, 2, 2);
        repo.registerEventInAuditorium(2, 3, 2);

        // Sample test case from screenshot
        System.out.println(service.startBookingProcess(7, 1)); // true
        System.out.println(service.startBookingProcess(7, 2)); // true
        System.out.println(service.startBookingProcess(7, 3)); // false
        System.out.println(service.confirmBookingStatus(7, 1, true)); // successful 2
        System.out.println(service.startBookingProcess(7, 4)); // false
    }
}