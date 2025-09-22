package practice.TinyURL;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TinyUrlApp {
    static class UrlMapping {
        String shortCode;
        String longUrl;
        LocalDateTime createdAt;
        LocalDateTime expiresAt;
        long accessCount;

        public UrlMapping(String shortCode, String longUrl, LocalDateTime expiresAt) {
            this.shortCode = shortCode;
            this.longUrl = longUrl;
            this.createdAt = LocalDateTime.now();
            this.expiresAt = expiresAt;
            this.accessCount = 0;
        }
    }
        private final Map<String,UrlMapping> store = new HashMap<>();
        private final AtomicLong counter = new AtomicLong(1000);
        private final String baseUrl = "http://tinyurl.com/";

        public String createShortUrl(String longUrl, LocalDateTime expiry){
            String shortCode = encodeBase62(counter.incrementAndGet());
            UrlMapping mapping = new UrlMapping(shortCode,longUrl,expiry);
            store.put(shortCode,mapping);
            return baseUrl + shortCode;
        }

        public String getLongUrl(String shortCode){
            UrlMapping mapping = store.get(shortCode);
            if(mapping ==  null) throw new IllegalArgumentException("Short URL not found");
            if(mapping.expiresAt != null && mapping.expiresAt.isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException();
            mapping.accessCount++;
            return mapping.longUrl;
        }

        private String encodeBase62(long num) {
            String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder sb = new StringBuilder();
            while(num > 0) {
                sb.append(chars.charAt((int)(num % 62)));
                num /=62;
            }
            return sb.reverse().toString();
        }

    public static void main(String[] args) {
        TinyUrlApp app = new TinyUrlApp();

        String longUrl = "https://www.example.com/some/very/long/url";
        String shortUrl = app.createShortUrl(longUrl, LocalDateTime.now().plusDays(1));
        System.out.println("Short URL: " + shortUrl);

        String shortCode = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        String resolved = app.getLongUrl(shortCode);
        System.out.println("Resolved URL: " + resolved);
    }

}
