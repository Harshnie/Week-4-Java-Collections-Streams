import java.util.*;
import java.util.stream.*;

public class OptionalHandlingExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(23, 56, 12, 89, 34);

        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);

        if (max.isPresent()) {
            System.out.println("Maximum value: " + max.get());
        } else {
            System.out.println("List is empty. No maximum value found.");
        }

        // Example with empty list
        List<Integer> emptyList = new ArrayList<>();

        Optional<Integer> emptyMax = emptyList.stream()
                .max(Integer::compareTo);

        System.out.println("\nEmpty list result:");
        emptyMax.ifPresentOrElse(
                value -> System.out.println("Maximum value: " + value),
                () -> System.out.println("List is empty. No maximum value found.")
        );
    }
}

