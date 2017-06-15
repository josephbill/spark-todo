import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Category {

    private int id;
    private String name;

    // Constructor

    public Category(String name) {
        this.name = name;
    }

    // Operators

    @Override
    public boolean equals(Object other) {
        if (other instanceof Category) {
            Category otherCategory = (Category) other;
            return this.name.equals(otherCategory.getName()) &&
                this.id == otherCategory.getId();
        } else {
            return false;
        }
    }

    // Public methods

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Task> getTasks() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM tasks WHERE categoryId=:cid";
            return con.createQuery(sql)
                    .addParameter("cid", this.id)
                    .executeAndFetch(Task.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO categories (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                                .addParameter("name", this.name)
                                .executeUpdate()
                                .getKey();
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            // First delete the associated tasks
            String sql = "DELETE FROM tasks WHERE categoryId=:id";
            con.createQuery(sql)
                .addParameter("id", this.id)
                .executeUpdate();
            // Now delete the category
            sql = "DELETE FROM categories WHERE id=:id";
            con.createQuery(sql)
                .addParameter("id", this.id)
                .executeUpdate();
        }
    }

    // Static methods

    public static List<Category> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT id, name FROM categories";
            return con.createQuery(sql).executeAndFetch(Category.class);
        }
    }

    public static Category find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT id, name FROM categories WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Category.class);
        }
    }
}
