//package practice;
//
//import java.time.LocalDateTime;
//
//public class TinyURL {
//}
//class UrlMapping {
//    private String shortCode;         // unique 6-8 char code
//    private String longUrl;           // original URL
//    private LocalDateTime createdAt;
//    private LocalDateTime expiresAt;  // nullable
//    private long accessCount;         // analytics
//
//    // getters/setters
//}
//interface UrlRepository {
//    void save(UrlMapping mapping);
//    UrlMapping findByShortCode(String shortCode);
//    void incrementAccessCount(String shortCode);
//}
//
////CREATE TABLE url_mapping (
////        short_code VARCHAR(10) PRIMARY KEY,
////long_url TEXT NOT NULL,
////created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
////expires_at TIMESTAMP NULL,
////access_count BIGINT DEFAULT 0
////        );
////CREATE INDEX idx_long_url ON url_mapping(long_url);
//
//
//public class UrlShortenerService {
//    private UrlRepository repository;
//    private IdGenerator idGenerator;
//
//    public String createShortUrl(String longUrl, LocalDateTime expiry) {
//        String shortCode = idGenerator.generate();
//        UrlMapping mapping = new UrlMapping(shortCode, longUrl,
//                LocalDateTime.now(), expiry, 0);
//        repository.save(mapping);
//        return "https://tinyurl.com/" + shortCode;
//    }
//
//    public String getLongUrl(String shortCode) {
//        UrlMapping mapping = repository.findByShortCode(shortCode);
//        if (mapping == null || (mapping.getExpiresAt()!=null &&
//                mapping.getExpiresAt().isBefore(LocalDateTime.now()))) {
//            throw new RuntimeException("Invalid or expired short URL");
//        }
//        repository.incrementAccessCount(shortCode);
//        return mapping.getLongUrl();
//    }
//}
