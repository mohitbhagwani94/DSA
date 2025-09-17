package practice.BookingService;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Auditorium model
class Auditorium {
    int auditoriumNumber;
    int eventId;
    int capacity;

    Auditorium(int auditoriumNumber, int eventId, int capacity) {
        this.auditoriumNumber = auditoriumNumber;
        this.eventId = eventId;
        this.capacity = capacity;
    }
}

// Repo implementation
class Repo implements IRepo {
    Map<Integer,Auditorium> eventToAudi = new HashMap<>();
    Map<Integer, Set<Integer>> underbooking= new HashMap<>();
    Map<Integer,Set<Integer>> booked = new HashMap<>();

    @Override
    public void registerEventInAuditorium(int auditoriumNumber, int eventId, int capacity) {
        eventToAudi.put(eventId, new Auditorium(auditoriumNumber,eventId, capacity));
    }

    @Override
    public Auditorium getAuditoriumDetailsForEvent(int eventId) {
        return eventToAudi.get(eventId);
    }

    @Override
    public int getNumberOfSeatsBooksOrUnderBookingForEvent(int eventId) {
        int count = 0;
        if(underbooking.containsKey(eventId))
            count += underbooking.get(eventId).size();

        if(booked.containsKey(eventId))
            count += booked.get(eventId).size();

        return count;
    }

    @Override
    public void addUnderBooking(int eventId, int userId) {
        underbooking.putIfAbsent(eventId,new HashSet<Integer>());
        underbooking.get(eventId).add(userId);
    }

    @Override
    public void removeUnderBooking(int eventId, int userId) {
        if(underbooking.containsKey(eventId)){
            underbooking.get(eventId).remove(userId);
        }
    }

    @Override
    public void addSuccessfulBooking(int eventId, int userId) {
        booked.putIfAbsent(eventId, new HashSet<>());
        booked.get(eventId).add(userId);
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
        Auditorium aud = repo.getAuditoriumDetailsForEvent(eventId);
        if(aud == null) return false;

        int currSeatStatus = repo.getNumberOfSeatsBooksOrUnderBookingForEvent(eventId);
        if(currSeatStatus < aud.capacity){
               repo.addUnderBooking(eventId,userId);
               return true;
        }
        return false;
    }

    @Override
    public BookingConclusion confirmBookingStatus(int eventId, int userId, boolean bookingSuccessful) {
        Auditorium aud = repo.getAuditoriumDetailsForEvent(eventId);
        repo.removeUnderBooking(eventId,userId);
        if(bookingSuccessful){
            repo.addSuccessfulBooking(eventId,userId);
            return new BookingConclusion("Successful", aud.auditoriumNumber);
        }
        return new BookingConclusion("Failed", -1);

    }
}
