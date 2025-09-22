package practice.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringFrequencyCounter {
    public static Map<String,Long> getFrequency(List<String> strings){
        if(strings == null)
            return Collections.emptyMap();

        return strings.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }
    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "banana", "apple", null, "banana", "apple", "orange", null);

        Map<String, Long> frequencyMap = getFrequency(input);

        System.out.println(frequencyMap);
        // Output: {orange=1, banana=2, apple=3}
    }

}
