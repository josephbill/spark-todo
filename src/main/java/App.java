import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;


public class App {

    public static void main(String[] args)
    {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String,Object> context = new HashMap<String,Object>();
            context.put("template", "templates/index.vtl");
            context.put("tasks", request.session().attribute("tasks"));

            return new ModelAndView(context, layout);
        }, new VelocityTemplateEngine());

        post("/", (request, response) -> {
            ArrayList<Task> tasks = request.session().attribute("tasks");
            if (tasks == null) {
                tasks = new ArrayList<Task>();
                request.session().attribute("tasks", tasks);
            }

            String description = request.queryParams("description");
            Task newTask = new Task(description);
            tasks.add(newTask);

            Map<String,Object> context = new HashMap<String,Object>();
            context.put("template", "templates/index.vtl");
            context.put("tasks", request.session().attribute("tasks"));

            return new ModelAndView(context, layout);
        }, new VelocityTemplateEngine());
    }
}
