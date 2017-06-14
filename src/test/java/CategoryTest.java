import org.junit.*;
import static org.junit.Assert.*;


public class CategoryTest {

    @Before
    public void clean_start() {
        Category.clear();
    }

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
        assertEquals(Category.all().contains(c2), true);
        assertEquals(Category.all().contains(c1), true);
    }

    @Test
    public void clear_all_categories() {
        Category c = new Category("Home");
        Category.clear();
        assertEquals(Category.all().size(), 0);
    }

    @Test
    public void Category_has_ID() {
        Category c1 = new Category("Home");
        Category c2 = new Category("Work");
        assertEquals(c1.getId(), 1);
        assertEquals(c2.getId(), 2);
    }

    @Test
    public void find_category_by_id() {
        Category c1 = new Category("Home");
        Category c2 = new Category("Work");
        assertEquals(Category.find(c2.getId()), c2);
        assertEquals(Category.find(c1.getId()), c1);
    }

    @Test
    public void category_instantiates_with_empty_task_list() {
        Category c = new Category("Home");
        assertEquals(c.getTasks().size(), 0);
    }

    @Test
    public void add_task_to_category() {
        Category c = new Category("Home");
        Task t = new Task("Mow the lawn");
        c.addTask(t);
        assertEquals(c.getTasks().size(), 1);
        assertTrue(c.getTasks().contains(t));
    }

    @Test
    public void find_returns_null_for_nonexisting_id() {
        assertTrue(Category.find(999) == null);
    }
}
