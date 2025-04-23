
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {

    public boolean isEven(int number) {
        return number % 2 == 0;
    }


    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 7, 9})
    public void testIsEven(int number) {
        if (number % 2 == 0) {
            assertTrue(isEven(number), number + " should be even");
        } else {
            assertFalse(isEven(number), number + " should be odd");
        }
    }

    public String longRunningTask() throws InterruptedException {
        Thread.sleep(3000); // Simulates a 3-second delay
        return "done";
    }

    @Test
    @Timeout(5)
    public void testLongRunningTaskTimeout() throws InterruptedException {
        String result = longRunningTask();
        assertEquals("done", result);
    }
}
