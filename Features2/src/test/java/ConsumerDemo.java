import java.util.*;
import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie", "david");

        Consumer<String> printUpperCase = name -> System.out.println(name.toUpperCase());

        names.forEach(printUpperCase);
    }
}

