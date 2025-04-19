package Collections;

import java.util.*;

public class RemoveDuplicate {
    public static List<Integer> removeDuplicates(List<Integer> list) {
        Set<Integer> seen = new LinkedHashSet<>(list);
        return new ArrayList<>(seen);
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(3, 1, 2, 2, 3, 4);
        List<Integer> output = removeDuplicates(input);
        System.out.println(output);
    }
}
