import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    static class PasswordValidator {

        public boolean isValid(String password) {
            if (password == null) return false;
            if (password.length() < 8) return false;

            boolean hasUppercase = false;
            boolean hasDigit = false;

            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    hasUppercase = true;
                }
                if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
            }

            return hasUppercase && hasDigit;
        }
    }

    private final PasswordValidator validator = new PasswordValidator();


    @Test
    public void testValidPassword() {
        assertTrue(validator.isValid("Password1"));
        assertTrue(validator.isValid("StrongPass9"));
        assertTrue(validator.isValid("A12345678"));
    }

    @Test
    public void testTooShortPassword() {
        assertFalse(validator.isValid("P1a"));
        assertFalse(validator.isValid("P7xK1"));
    }

    @Test
    public void testMissingUppercase() {
        assertFalse(validator.isValid("password1"));
        assertFalse(validator.isValid("strongpass9"));
    }

    @Test
    public void testMissingDigit() {
        assertFalse(validator.isValid("Password"));
        assertFalse(validator.isValid("NoNumbersHere"));
    }

    @Test
    public void testNullPassword() {
        assertFalse(validator.isValid(null));
    }
}
