package BookingService;

import java.util.HashSet;

public interface IRepo {
    public void registerEventInAuditorium(int auditoriumNumber, int eventId, int capacity);

    public Auditorium getAuditoriumDetailsForEvent(int eventId);

    public int getNumberOfSeatsBooksOrUnderBookingForEvent(int eventId);

    public void addUnderBooking(int eventId, int userId);

    public void removeUnderBooking(int eventId, int userId);

    public void addSuccessfulBooking(int eventId, int userId);
}
