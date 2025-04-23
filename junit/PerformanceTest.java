import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {

    public String longRunningTask() {
        try {
            Thread.sleep(3000); // Sleep for 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Done";
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    public void testLongRunningTaskTimeout() {
        PerformanceTest test = new PerformanceTest();
        String result = test.longRunningTask();
        assertEquals("Done", result);
    }
}
