
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtils {

    public String reverse(String str) {
        if (str == null) return null;
        return new StringBuilder(str).reverse().toString();
    }

    public boolean isPalindrome(String str) {
        if (str == null) return false;
        return str.equalsIgnoreCase(reverse(str));
    }

    public String toUpperCase(String str) {
        if (str == null) return null;
        return str.toUpperCase();
    }


    @Test
    public void testReverse() {
        StringUtils utils = new StringUtils();
        assertEquals("dcba", utils.reverse("abcd"));
        assertEquals("", utils.reverse(""));
        assertNull(utils.reverse(null));
    }

    @Test
    public void testIsPalindrome() {
        StringUtils utils = new StringUtils();
        assertTrue(utils.isPalindrome("madam"));
        assertTrue(utils.isPalindrome("RaceCar"));
        assertFalse(utils.isPalindrome("hello"));
        assertFalse(utils.isPalindrome(null));
    }

    @Test
    public void testToUpperCase() {
        StringUtils utils = new StringUtils();
        assertEquals("HELLO", utils.toUpperCase("hello"));
        assertEquals("JAVA", utils.toUpperCase("Java"));
        assertNull(utils.toUpperCase(null));
    }
}
