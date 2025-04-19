package Collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class FrequencyCounter {

    public static Map<String, Integer> countFrequency(List<String> list) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        for (String element : list) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }
        
        return frequencyMap;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputList = new ArrayList<>();
        
        System.out.println("Enter strings separated by spaces (e.g., apple banana apple orange):");
        String[] inputs = scanner.nextLine().split(" ");
        
        for (String input : inputs) {
            inputList.add(input.trim());
        }
        
        Map<String, Integer> frequencyMap = countFrequency(inputList);
        
        System.out.println("\nFrequency of elements:");
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        
        scanner.close();
    }
}
