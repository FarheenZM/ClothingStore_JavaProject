package controllers;

import db.DBHelper;
import models.Category;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
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
            List<Product> newProducts = new ArrayList<>();
            for( Product product : products) {
                if (product.getCategory() == Category.NEW_IN) {
                    newProducts.add(product);
                    model.put("template", "templates/products/newIn/index.vtl");
                    model.put("newProducts", newProducts);
                }
            }
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        get("/clothes", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> clothes = new ArrayList<>();
            for( Product product : products) {
                if (product.getCategory() == Category.CLOTHES) {
                    clothes.add(product);
                    model.put("template", "templates/products/clothes/index.vtl");
                    model.put("clothes", clothes);
                }
            }
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/shoes", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> shoes = new ArrayList<>();
            for( Product product : products) {
                if (product.getCategory() == Category.SHOES) {
                    shoes.add(product);
                    model.put("template", "templates/products/shoes/index.vtl");
                    model.put("shoes", shoes);
                }
            }
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/accessories", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> accessories = new ArrayList<>();
            for( Product product : products){
                if (product.getCategory() == Category.ACCESSORIES){
                    accessories.add(product);
                    model.put("template", "templates/products/accessories/index.vtl");
                    model.put("accessories", accessories);
                }
            }
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }


}
