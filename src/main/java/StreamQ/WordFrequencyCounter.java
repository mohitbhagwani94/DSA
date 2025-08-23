package StreamQ;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String paragraph = "Java is great. Java is powerful. Streams in Java are great.";

        Map<String, Long> wordCount = Arrays.stream(paragraph.split("\\W+"))
                        .map(String :: toLowerCase)
                        .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        wordCount.forEach((word, count) ->
                System.out.println(word + ": " + count));
    }
}
