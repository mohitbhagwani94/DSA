package practice.template.BookingService;

// Auditorium model
class Auditorium {

}

// Repo implementation
class Repo implements IRepo {


    @Override
    public void registerEventInAuditorium(int auditoriumNumber, int eventId, int capacity) {

    }

    @Override
    public Auditorium getAuditoriumDetailsForEvent(int eventId) {


    }

    @Override
    public int getNumberOfSeatsBooksOrUnderBookingForEvent(int eventId) {

    }

    @Override
    public void addUnderBooking(int eventId, int userId) {

    }

    @Override
    public void removeUnderBooking(int eventId, int userId) {

    }

    @Override
    public void addSuccessfulBooking(int eventId, int userId) {

    }
}

// Booking Service
class BookingService implements IBookingService {
    public IRepo repo;

    public BookingService(IRepo repo) {
        this.repo = repo;
    }

    @Override
    public boolean startBookingProcess(int eventId, int userId) {

    }

    @Override
    public BookingConclusion confirmBookingStatus(int eventId, int userId, boolean bookingSuccessful) {

    }
}
