import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;


public class App {

    public static void main(String[] args)
    {
        // staticFileLocation("/public");
        // String layout = "templates/layout.vtl";
        //
        // // Home
        // get("/", (request, response) -> {
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/index.vtl");
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // List categories
        // get("/categories", (request, response) -> {
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/categories.vtl");
        //     model.put("categories", Category.all());
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // New category
        // get("/categories/new", (request, response) -> {
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/category_form.vtl");
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // New task submit
        // post("/categories", (request, response) -> {
        //     Category newCategory = new Category(request.queryParams("name"));
        //
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/category_success.vtl");
        //     model.put("category", newCategory);
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // Category detail
        // get("/categories/:id", (request, response) -> {
        //     Category c = Category.find(Integer.parseInt(request.params(":id")));
        //
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/category.vtl");
        //     model.put("category", c);
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // New task
        // get("/categories/:id/tasks/new", (request, response) -> {
        //     Category c = Category.find(Integer.parseInt(request.params(":id")));
        //
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/category_task_form.vtl");
        //     model.put("category", c);
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // New task submit
        // post("/tasks", (request, response) -> {
        //     Task t = new Task(request.queryParams("description"));
        //     Category c = Category.find(Integer.parseInt(request.queryParams("category_id")));
        //     c.addTask(t);
        //
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/category_task_success.vtl");
        //     model.put("task", t);
        //     model.put("category", c);
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // // Task detail page
        // get("/tasks/:id", (request, response) -> {
        //     Task task = Task.find(Integer.parseInt(request.params(":id")));
        //
        //     Map<String,Object> model = new HashMap<String,Object>();
        //     model.put("template", "templates/task.vtl");
        //     model.put("task", task);
        //
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
    }
}
