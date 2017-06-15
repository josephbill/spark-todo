import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Task {

    private int id;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private int categoryId;

    // constructor

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.categoryId = 1;
    }

    public Task(String description, int categoryId) {
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.categoryId = categoryId;
    }

    // operators

    @Override
    public boolean equals(Object otherTask) {
        if (!(otherTask instanceof Task)) {
            return false;
        } else {
            Task newTask = (Task) otherTask;
            return this.description.equals(newTask.getDescription()) &&
                    this.id == newTask.getId() &&
                    this.categoryId == newTask.getCategoryId();
        }
    }

    // public methods

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO tasks (description, categoryId) VALUES (:description, :categoryId)";
            this.id = (int) con.createQuery(sql, true)
                                .addParameter("description", this.description)
                                .addParameter("categoryId", this.categoryId)
                                .executeUpdate()
                                .getKey();
        }
    }

    // static methods

    public static List<Task> all() {
        String sql = "SELECT id, description, categoryId FROM tasks";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Task.class);
        }
    }

    public static Task find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM tasks WHERE id=:id";
            Task task = con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Task.class);
            return task;
        }
    }
}
