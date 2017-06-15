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
            model.put("categories", Category.all());

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // List categories
        get("/categories", (request, response) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/categories.vtl");
            model.put("categories", Category.all());

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // New category
        get("/categories/new", (request, response) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/category_form.vtl");

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // New category submit
        post("/categories", (request, response) -> {
            Category newCategory = new Category(request.queryParams("name"));
            newCategory.save();

            response.redirect("/");

            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Category detail
        get("/categories/:id", (request, response) -> {
            Category c = Category.find(Integer.parseInt(request.params(":id")));

            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/category.vtl");
            model.put("category", c);

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // New task
        get("/categories/:id/tasks/new", (request, response) -> {
            Category c = Category.find(Integer.parseInt(request.params(":id")));

            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/category_task_form.vtl");
            model.put("category", c);

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // New task submit
        post("/tasks", (request, response) -> {
            Category c = Category.find(Integer.parseInt(request.queryParams("categoryId")));
            Task t = new Task(request.queryParams("description"), c.getId());
            t.save();

            String url = String.format("/categories/%d", c.getId());
            response.redirect(url);

            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Task update page
        get("/categories/:cid/tasks/:tid", (request, response) -> {
            Task t = Task.find(Integer.parseInt(request.params(":tid")));
            Category c = Category.find(Integer.parseInt(request.params(":cid")));

            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template", "templates/task.vtl");
            model.put("task", t);
            model.put("category", c);

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Task update submit
        post("/categories/:cid/tasks/:tid", (request, response) -> {
            Task t = Task.find(Integer.parseInt(request.params(":tid")));
            Category c = Category.find(Integer.parseInt(request.params(":cid")));
            String description = request.queryParams("description");
            t.update(description);

            String url = String.format("/categories/%d/tasks/%d", c.getId(), t.getId());
            response.redirect(url);

            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Task delete submit
        post("/categories/:cid/tasks/:tid/delete", (request, response) -> {
            Task t = Task.find(Integer.parseInt(request.params(":tid")));
            Category c = Category.find(Integer.parseInt(request.params(":cid")));
            t.delete();

            String url = String.format("/categories/%d", c.getId());
            response.redirect(url);

            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Category delete submit
        post("/categories/:cid/delete", (request, response) -> {
            Category c = Category.find(Integer.parseInt(request.params(":cid")));
            for (Task t : c.getTasks()) {
                t.delete();
            }
            c.delete();

            response.redirect("/");

            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
