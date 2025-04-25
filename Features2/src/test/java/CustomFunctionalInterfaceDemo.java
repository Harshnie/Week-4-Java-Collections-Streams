@FunctionalInterface
interface SquareCalculator {
    int calculate(int x);

    default void printResult(int x) {
        System.out.println("The square of " + x + " is: " + calculate(x));
    }
}

public class CustomFunctionalInterfaceDemo {
    public static void main(String[] args) {
        SquareCalculator calculator = (n) -> n * n;

        int number = 7;
        calculator.printResult(number);
    }
}

