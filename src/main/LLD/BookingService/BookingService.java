package BookingService;

import java.io.*;
import java.util.*;

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
    private Map<Integer, Auditorium> eventToAuditorium = new HashMap<>();
    private Map<Integer, Set<Integer>> underBooking = new HashMap<>();
    private Map<Integer, Set<Integer>> successfulBooking = new HashMap<>();

    @Override
    public void registerEventInAuditorium(int auditoriumNumber, int eventId, int capacity) {
        eventToAuditorium.put(eventId, new Auditorium(auditoriumNumber, eventId, capacity));
    }

    @Override
    public Auditorium getAuditoriumDetailsForEvent(int eventId) {
        return eventToAuditorium.get(eventId);
    }

    @Override
    public int getNumberOfSeatsBooksOrUnderBookingForEvent(int eventId) {
        int count = 0;
        if (underBooking.containsKey(eventId)) count += underBooking.get(eventId).size();
        if (successfulBooking.containsKey(eventId)) count += successfulBooking.get(eventId).size();
        return count;
    }

    @Override
    public void addUnderBooking(int eventId, int userId) {
        underBooking.putIfAbsent(eventId, new HashSet<>());
        underBooking.get(eventId).add(userId);
    }

    @Override
    public void removeUnderBooking(int eventId, int userId) {
        if (underBooking.containsKey(eventId)) {
            underBooking.get(eventId).remove(userId);
        }
    }

    @Override
    public void addSuccessfulBooking(int eventId, int userId) {
        successfulBooking.putIfAbsent(eventId, new HashSet<>());
        successfulBooking.get(eventId).add(userId);
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
        if (aud == null) return false;

        int currentSeats = repo.getNumberOfSeatsBooksOrUnderBookingForEvent(eventId);
        if (currentSeats < aud.capacity) {
            repo.addUnderBooking(eventId, userId);
            return true;
        }
        return false;
    }

    @Override
    public BookingConclusion confirmBookingStatus(int eventId, int userId, boolean bookingSuccessful) {
        Auditorium aud = repo.getAuditoriumDetailsForEvent(eventId);
        repo.removeUnderBooking(eventId, userId);

        if (bookingSuccessful) {
            repo.addSuccessfulBooking(eventId, userId);
            return new BookingConclusion("successful", aud.auditoriumNumber);
        }
        return new BookingConclusion("failed", -1);
    }
}
