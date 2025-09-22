package practice.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private final int maxRequests;
    private final long windowMillis;

    public RateLimiter(int maxRequests, long windowMillis) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    private final ConcurrentHashMap<String, Window> userWindow = new ConcurrentHashMap<>();

    private static class Window {
        long windowStart;
        AtomicInteger count;
        Window(long windowStart){
            this.windowStart = windowStart;
            this.count = new AtomicInteger(0);
        }
    }

    public boolean allowRequest(String userId){
        long now = System.currentTimeMillis();
        Window window = userWindow.computeIfAbsent(userId,k->new Window(now));
        if(now - window.windowStart >= windowMillis){
            window.windowStart = now;
            window.count.set(0);
        }
        if(window.count.incrementAndGet() <= maxRequests){
            return true;
        }else{
            return false;
        }
    }
    // Demo
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(3, 2000); // 3 requests per 2 seconds per user
        String user = "alice";

        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.printf("Request %d allowed=%s%n", i, allowed);
            Thread.sleep(500); // simulate some delay
        }

        Thread.sleep(2000); // wait for window reset
        System.out.println("---- After waiting ----");
        for (int i = 1; i <= 3; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.printf("Request %d allowed=%s%n", i, allowed);
        }
    }

}
