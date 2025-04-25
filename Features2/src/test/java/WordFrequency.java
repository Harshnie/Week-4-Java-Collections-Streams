import java.util.*;
import java.util.stream.*;

public class WordFrequency {

    public static void main(String[] args) {
        String text = "Java is a popular programming language. Java is also widely used for backend development, Android apps, and enterprise applications. Java streams and lambdas are powerful.";

        int topN = 5;

        Map<String, Long> frequencyMap = Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        List<Map.Entry<String, Long>> topWords = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());

        System.out.println("Top " + topN + " most frequent words:");
        topWords.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
