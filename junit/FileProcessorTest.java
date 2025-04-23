import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorTest {

    static class FileProcessor {
        public void writeToFile(String filename, String content) throws IOException {
            Path path = Paths.get(filename);
            Files.writeString(path, content);
        }

        public String readFromFile(String filename) throws IOException {
            Path path = Paths.get(filename);
            return Files.readString(path);
        }
    }

    private static final String TEST_FILE = "testfile.txt";
    private final FileProcessor fileProcessor = new FileProcessor();

    @BeforeEach
    public void setUp() throws IOException {
        Files.deleteIfExists(Path.of(TEST_FILE));
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(TEST_FILE));
    }

    @Test
    public void testWriteAndReadFromFile() throws IOException {
        String content = "Hello, file!";
        fileProcessor.writeToFile(TEST_FILE, content);

        String readContent = fileProcessor.readFromFile(TEST_FILE);
        assertEquals(content, readContent);
    }

    @Test
    public void testFileExistsAfterWrite() throws IOException {
        fileProcessor.writeToFile(TEST_FILE, "Some content");
        assertTrue(Files.exists(Path.of(TEST_FILE)));
    }

    @Test
    public void testIOExceptionWhenFileDoesNotExist() {
        Exception exception = assertThrows(IOException.class, () -> {
            fileProcessor.readFromFile("non_existent_file.txt");
        });

        assertTrue(exception.getMessage().contains("non_existent_file"));
    }
}
