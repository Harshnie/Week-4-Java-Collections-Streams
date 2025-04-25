import java.util.Scanner;
import java.util.function.Function;

public class CircleAreaCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter radius of the circle: ");
        double radius = scanner.nextDouble();

        Function<Double, Double> calculateArea = r -> Math.PI * r * r;

        double area = calculateArea.apply(radius);

        System.out.println("Area of the circle: " + area);
    }
}
