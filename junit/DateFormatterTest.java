import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Main class containing both logic and test
public class DateFormatterTest {

    // Inner class to format the date
    static class DateFormatter {
        public String formatDate(String inputDate) {
            if (inputDate == null) {
                return "Invalid date";
            }

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            inputFormat.setLenient(false); // Strict validation

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

            try {
                Date date = inputFormat.parse(inputDate);
                return outputFormat.format(date);
            } catch (ParseException e) {
                return "Invalid date";
            }
        }
    }

    private DateFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new DateFormatter();
    }

    @Test
    public void testValidDateFormatting() {
        assertEquals("01-05-2024", formatter.formatDate("2024-05-01"));
        assertEquals("14-12-1999", formatter.formatDate("1999-12-14"));
        assertEquals("29-02-2024", formatter.formatDate("2024-02-29")); // Leap year
    }

    @Test
    public void testInvalidDateFormat() {
        assertEquals("Invalid date", formatter.formatDate("2023-02-29")); // Not a leap year
        assertEquals("Invalid date", formatter.formatDate("2024/05/01")); // Wrong format
        assertEquals("Invalid date", formatter.formatDate("01-05-2024")); // Wrong format
        assertEquals("Invalid date", formatter.formatDate("invalid-date")); // Random text
        assertEquals("Invalid date", formatter.formatDate(null)); // Null input
    }
}
