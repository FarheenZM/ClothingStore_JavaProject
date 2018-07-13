package controllers;

import db.DBHelper;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class ProductController {


    public ProductController(){
        setupRoutes();
    }

    public void setupRoutes(){

        get("/newin", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            model.put("template", "templates/products/newIn.vtl");
            model.put("products", products);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        get("/clothes", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            model.put("template", "templates/products/clothes.vtl");
            model.put("products", products);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/shoes", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            model.put("template", "templates/products/shoes.vtl");
            model.put("products", products);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/accessories", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            model.put("template", "templates/products/accessories.vtl");
            model.put("products", products);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }


}
