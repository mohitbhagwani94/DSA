package BookingService;

public interface IBookingService {

    public boolean startBookingProcess(int eventId, int userId);

    public BookingConclusion confirmBookingStatus(int eventId, int userId, boolean bookingSuccessful);
}
