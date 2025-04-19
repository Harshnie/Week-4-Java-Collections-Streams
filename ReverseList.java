package Collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReverseList {

    public static List<Integer> reverseArrayList(List<Integer> list) {
        List<Integer> reversed = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }

    public static List<Integer> reverseLinkedList(LinkedList<Integer> list) {
        LinkedList<Integer> reversed = new LinkedList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            arrayList.add(i);
        }
        System.out.println("Original ArrayList: " + arrayList);
        List<Integer> reversedArrayList = reverseArrayList(arrayList);
        System.out.println("Reversed ArrayList: " + reversedArrayList);

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            linkedList.add(i);
        }
        System.out.println("Original LinkedList: " + linkedList);
        List<Integer> reversedLinkedList = reverseLinkedList(linkedList);
        System.out.println("Reversed LinkedList: " + reversedLinkedList);
    }
}

