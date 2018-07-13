package controllers;

import db.DBHelper;
import db.Seeds;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class MainController {

    public static void main(String[] args) {

        ProductController productController = new ProductController();
//        Seeds.seedData();

        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/home.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
