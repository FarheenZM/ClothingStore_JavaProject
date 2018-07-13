package controllers;

import db.DBHelper;
import models.Category;
import models.Product;
import models.Shop;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

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


        get("/saved", (res, req) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/products/saved.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/addProduct", (res, req) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<Category> categories = DBHelper.getAllCategories(); //for dropdownlist
            model.put("categories", categories);

            model.put("template", "templates/products/addProduct.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/add", (req, res) -> {
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            int price = Integer.parseInt(req.queryParams("price"));
            String image = req.queryParams("image");
            List<Shop> shops = DBHelper.getAll(Shop.class);
            Shop shop = shops.get(0);

            Product product = new Product(title, description, category, price, image, shop);
            DBHelper.save(product);
            res.redirect("/saved");
            return null;
        }, new VelocityTemplateEngine());


    }


}
