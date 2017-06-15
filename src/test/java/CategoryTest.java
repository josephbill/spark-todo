import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class CategoryTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void category_instantiates_correctly() {
        Category c = new Category("Home");
        assertEquals(c instanceof Category, true);
    }

    @Test
    public void category_instantiates_with_name() {
        Category c = new Category("Home");
        assertEquals(c.getName(), "Home");
    }

    @Test
    public void list_all_categories() {
        Category c1 = new Category("Home");
        Category c2 = new Category("Work");
        c1.save();
        c2.save();
        assertTrue(Category.all().contains(c2));
        assertTrue(Category.all().contains(c1));
    }

    // @Test
    // public void clear_all_categories() {
    //     Category c = new Category("Home");
    //     Category.clear();
    //     assertEquals(Category.all().size(), 0);
    // }

    @Test
    public void Category_has_ID() {
        Category c1 = new Category("Home");
        c1.save();
        Category c2 = new Category("Work");
        c2.save();
        assertTrue(c1.getId() != c2.getId());
    }

    @Test
    public void find_category_by_id() {
        Category c1 = new Category("Home");
        Category c2 = new Category("Work");
        c1.save();
        c2.save();
        assertEquals(Category.find(c2.getId()), c2);
        assertEquals(Category.find(c1.getId()), c1);
    }

    @Test
    public void category_instantiates_with_empty_task_list() {
        Category c = new Category("Home");
        assertEquals(c.getTasks().size(), 0);
    }

    // @Test
    // public void add_task_to_category() {
    //     Category c = new Category("Home");
    //     Task t = new Task("Mow the lawn");
    //     c.addTask(t);
    //     assertEquals(c.getTasks().size(), 1);
    //     assertTrue(c.getTasks().contains(t));
    // }

    @Test
    public void find_returns_null_for_nonexisting_id() {
        assertTrue(Category.find(999) == null);
    }

    @Test
    public void category_equality() {
        Category c1 = new Category("Home");
        Category c2 = new Category("Home");
        assertTrue(c1.equals(c2));
    }

    @Test
    public void save_category_to_db() {
        Category c = new Category("Home");
        c.save();
        assertTrue(Category.all().get(0).equals(c));
    }

    @Test
    public void save_assignes_unique_id() {
        Category c = new Category("Home");
        c.save();
        assertEquals(Category.all().get(0).getId(), c.getId());
    }

    @Test
    public void get_all_tasks_in_a_category() {
        Category c = new Category("Home");
        c.save();
        Task t1 = new Task("Clean toilet", c.getId());
        t1.save();
        Task t2 = new Task("Buy foods", c.getId());
        t2.save();
        Task[] tasks = new Task[] {t1, t2};
        assertTrue(c.getTasks().containsAll(Arrays.asList(tasks)));
    }
}
