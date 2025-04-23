// JUnit 5 imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EvenNumberTest {

    // --- Method to test ---
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 7, 9})
    public void testIsEven(int number) {
        EvenNumberTest testObj = new EvenNumberTest();
        if (number % 2 == 0) {
            assertTrue(testObj.isEven(number), number + " should be even");
        } else {
            assertFalse(testObj.isEven(number), number + " should be odd");
        }
    }
}
