import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;


public class TaskTest {

    @Before
    public void setup() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/todo_test", null, null);
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM tasks *;";
            con.createQuery(sql).executeUpdate();
        }
    }

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

    @Test
    public void Task_is_incomplete_when_created() {
        Task t = new Task("Mow the lawn");
        assertEquals(false, t.isCompleted());
    }

    @Test
    public void Task_is_created_with_current_timestamp() {
        Task t = new Task("Mow the lawn");
        assertEquals(LocalDateTime.now().getDayOfWeek(), t.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void all_returns_all_instances_of_Task() {
        Task t1 = new Task("Mow the lawn");
        t1.save();
        Task t2 = new Task("Buy groceries");
        t2.save();
        assertEquals(true, Task.all().contains(t1));
        assertEquals(true, Task.all().contains(t2));
    }

    // @Test
    // public void clear_all_tasks() {
    //     Task t1 = new Task("Mow the lawn");
    //     Task t2 = new Task("Buy groceries");
    //     Task.clear();
    //     assertEquals(0, Task.all().size());
    // }

    // @Test
    // public void tasks_are_created_with_IDs() {
    //     Task t1 = new Task("Mow the lawn");
    //     t1.save();
    //     assertEquals(1, t1.getId());
    //     Task t2 = new Task("Buy groceries");
    //     t2.save();
    //     assertEquals(2, t2.getId());
    // }

    @Test
    public void find_Task_with_ID() {
        Task t1 = new Task("Mow the lawn");
        t1.save();
        Task t2 = new Task("Buy groceries");
        t2.save();
        assertEquals(Task.find(t2.getId()), t2);
        assertEquals(Task.find(t1.getId()), t1);
    }

    @Test
    public void equal_if_same_description() {
        Task t1 = new Task("Mow the lawn");
        Task t2 = new Task("Mow the lawn");
        assertTrue(t1.equals(t2));
    }

    @Test
    public void save_task_to_db() {
        Task t = new Task("Mow the lawn");
        t.save();
        assertTrue(Task.all().get(0).equals(t));
    }

    @Test
    public void save_assigns_unique_id() {
        Task t = new Task("Mow the lawn");
        t.save();
        Task savedTask = Task.all().get(0);
        assertEquals(t.getId(), savedTask.getId());
    }
}
