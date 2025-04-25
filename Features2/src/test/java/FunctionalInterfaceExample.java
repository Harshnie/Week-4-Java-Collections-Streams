@FunctionalInterface
interface Adder {
    int add(int a, int b);
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        Adder adder = (a, b) -> a + b;
        int num1 = 10;
        int num2 = 20;
        int result = adder.add(num1, num2);
        System.out.println("Sum of " + num1 + " and " + num2 + " is: " + result);
    }
}
