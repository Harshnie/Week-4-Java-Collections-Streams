import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FunctionalFeatures {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("testing", "example", "demo", "unit test", "pretest", "java", "contest");

        Predicate<String> lengthGreaterThanFive = word -> word.length() > 5;
        Predicate<String> containsTest = word -> word.contains("test");

        Predicate<String> combinedPredicate = lengthGreaterThanFive.and(containsTest);

        Consumer<String> printWord = word -> System.out.println("Filtered word: " + word);

        words.stream()
                .filter(combinedPredicate)
                .forEach(printWord);
    }
}

