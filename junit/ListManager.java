
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListManager {

    public void addElement(List<Integer> list, int element) {
        list.add(element);
    }

    public void removeElement(List<Integer> list, int element) {
        list.remove(Integer.valueOf(element));
    }

    public int getSize(List<Integer> list) {
        return list.size();
    }

    @Test
    public void testAddElement() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        manager.addElement(list, 10);
        manager.addElement(list, 20);
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertEquals(2, manager.getSize(list));
    }

    @Test
    public void testRemoveElement() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(25);
        manager.removeElement(list, 15);
        assertFalse(list.contains(15));
        assertEquals(2, manager.getSize(list));
    }

    @Test
    public void testGetSize() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        assertEquals(0, manager.getSize(list));
        list.add(1);
        list.add(2);
        assertEquals(2, manager.getSize(list));
    }
}
