import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationTest {

    static class UserRegistration {
        public String registerUser(String username, String email, String password) {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }

            if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("Invalid email format");
            }

            if (password == null || password.length() < 6) {
                throw new IllegalArgumentException("Password must be at least 6 characters long");
            }

            return "User registered successfully";
        }
    }

    private UserRegistration userRegistration;

    @BeforeEach
    public void setUp() {
        userRegistration = new UserRegistration();
    }

    @Test
    public void testValidRegistration() {
        String result = userRegistration.registerUser("JohnDoe", "john@example.com", "pass123");
        assertEquals("User registered successfully", result);
    }

    @Test
    public void testEmptyUsername() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userRegistration.registerUser("", "john@example.com", "pass123"));
        assertEquals("Username cannot be empty", exception.getMessage());
    }

    @Test
    public void testInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userRegistration.registerUser("John", "john_at_example.com", "pass123"));
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    public void testShortPassword() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userRegistration.registerUser("John", "john@example.com", "123"));
        assertEquals("Password must be at least 6 characters long", exception.getMessage());
    }

    @Test
    public void testNullInputs() {
        assertThrows(IllegalArgumentException.class, () ->
                userRegistration.registerUser(null, "john@example.com", "pass123"));

        assertThrows(IllegalArgumentException.class, () ->
                userRegistration.registerUser("John", null, "pass123"));

        assertThrows(IllegalArgumentException.class, () ->
                userRegistration.registerUser("John", "john@example.com", null));
    }
}
