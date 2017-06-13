import org.junit.*;
import static org.junit.Assert.*;


public class TaskTest {

    @Test
    public void Task_instantiatesCorrectly() {
        Task t = new Task("Mow the lawn");
        assertEquals(true, t instanceof Task);
    }

    @Test
    public void Task_instantiatesWithDescription() {
        Task t = new Task("Mow the lawn");
        assertEquals("Mow the lawn", t.getDescription());
    }
}
