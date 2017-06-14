import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;


public class App {

    public static void main(String[] args)
    {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        // Home
        get("/", (request, response) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/index.vtl");

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // List tasks
        get("/tasks", (request, response) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/tasks.vtl");
            model.put("tasks", Task.all());

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // New task
        get("/tasks/new", (request, response) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/task_form.vtl");

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // New task submit
        post("/tasks", (request, response) -> {
            Task newTask = new Task(request.queryParams("description"));

            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/success.vtl");
            model.put("task", newTask);

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Task detail page
        get("/tasks/:id", (request, response) -> {
            Task task = Task.find(Integer.parseInt(request.params(":id")));

            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/task.vtl");
            model.put("task", task);

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
