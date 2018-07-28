package controllers;

import db.DBHelper;
import db.Seeds;
import models.Category;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        staticFileLocation("/public");

        ProductController productController = new ProductController();
        UserController userController = new UserController();
        Seeds.seedData();


        get("/manager", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/home.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}

