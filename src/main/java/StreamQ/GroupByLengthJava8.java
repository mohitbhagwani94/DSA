package StreamQ;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByLengthJava8 {
    public static void main(String[] args) {
        // Example input
        List<String> words = Arrays.asList(
                "dog", "cat", "elephant", "rabbit", "fox", "giraffe", "ant", "zebra", "owl"
        );

        // Group strings by their length using Java 8 streams
        Map<Integer, List<String>> grouped = words.stream().collect(Collectors.groupingBy(String :: length));

        System.out.println(grouped);

        // Print results sorted by key (string length)
//        grouped.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEach(entry -> {
//                    int length = entry.getKey();
//                    List<String> group = entry.getValue();
//                    System.out.println("Strings of length " + length + ": "
//                            + group.size() + " string(s) -> " + group);
//                });
    }
}

