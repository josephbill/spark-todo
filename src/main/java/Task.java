import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Task {

    private int mId;
    private String mDescription;
    private boolean mCompleted;
    private LocalDateTime mCreatedAt;

    private static List<Task> sInstances = new ArrayList<Task>();

    // constructor

    public Task(String description) {
        mDescription = description;
        mCompleted = false;
        mCreatedAt = LocalDateTime.now();

        sInstances.add(this);
        mId = sInstances.size();
    }

    // public methods

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return mCreatedAt;
    }

    // static methods

    public static List<Task> all() {
        return sInstances;
    }

    public static void clear() {
        sInstances.clear();
    }

    public static Task find(int id) {
        try {
            return sInstances.get(id-1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
