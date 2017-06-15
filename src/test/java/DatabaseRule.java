import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/todo", null, null);
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String sqlTask = "DELETE FROM tasks *;";
            String sqlCategory = "DELETE FROM categories *;";
            con.createQuery(sqlTask).executeUpdate();
            con.createQuery(sqlCategory).executeUpdate();
        }
    }
}
