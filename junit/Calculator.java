// Import for JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Calculator {

    // --- Calculator Methods ---
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }

    // --- JUnit Test Methods ---
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(9, calc.add(5, 4));
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(5, 4));
    }

    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(20, calc.multiply(5, 4));
    }

    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.divide(8, 4));
    }

    @Test
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        });
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }
}
