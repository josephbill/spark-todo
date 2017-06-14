import java.util.List;
import java.util.ArrayList;


public class Category {

    private int mId;
    private String mName;
    private List<Task> mTasks;

    private static List<Category> sInstances = new ArrayList<Category>();

    // Constructor

    public Category(String name) {
        mName = name;
        sInstances.add(this);
        mId = sInstances.size();
        mTasks = new ArrayList<Task>();
    }

    // Public methods

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void addTask(Task task) {
        mTasks.add(task);
    }

    // Static methods

    public static List<Category> all() {
        return sInstances;
    }

    public static void clear() {
        sInstances.clear();
    }

    public static Category find(int id) {
        try {
            return sInstances.get(id-1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
