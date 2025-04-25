import java.util.*;
import java.util.stream.*;

public class SecondMostRepeatedWord {

    public static void main(String[] args) {
        String text = "apple banana apple orange banana apple mango orange mango banana";

        Map<String, Long> frequencyMap = Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        List<Map.Entry<String, Long>> sortedList = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        if (sortedList.size() < 2) {
            System.out.println("Not enough unique words to determine the second most frequent word.");
        } else {
            Map.Entry<String, Long> secondMost = sortedList.get(1);
            System.out.println("Second most repeated word: " + secondMost.getKey() + " (Count: " + secondMost.getValue() + ")");
        }
    }
}
