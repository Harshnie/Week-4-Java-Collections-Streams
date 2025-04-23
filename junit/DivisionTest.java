
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest {

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    @Test
    public void testDivideByZeroThrowsException() {
        DivisionTest dt = new DivisionTest();

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            dt.divide(10, 0);
        });

        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    public void testDivideValid() {
        DivisionTest dt = new DivisionTest();
        assertEquals(5, dt.divide(10, 2));
    }
}
