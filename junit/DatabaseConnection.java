
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnection {

    private boolean isConnected = false;

    public void connect() {
        isConnected = true;
        System.out.println("Connected to database.");
    }

    public void disconnect() {
        isConnected = false;
        System.out.println("Disconnected from database.");
    }

    public boolean isConnected() {
        return isConnected;
    }

    public static class DatabaseConnectionTest {

        private DatabaseConnection db;

        @BeforeEach
        public void setUp() {
            db = new DatabaseConnection();
            db.connect();
        }

        @AfterEach
        public void tearDown() {
            db.disconnect();
        }

        @Test
        public void testConnectionIsEstablished() {
            assertTrue(db.isConnected(), "Database should be connected after setUp()");
        }

        @Test
        public void testConnectionStillActiveDuringTest() {
            assertTrue(db.isConnected(), "Connection should remain open during the test");
        }

        @Test
        public void testManualDisconnect() {
            db.disconnect();
            assertFalse(db.isConnected(), "Database should be disconnected manually");
        }
    }
}
