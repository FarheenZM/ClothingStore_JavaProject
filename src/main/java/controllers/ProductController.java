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

        NewinController newinController = new NewinController();
        ClothesController clothesController = new ClothesController();
        ShoesController shoesController = new ShoesController();
        AccessoriesController accessoriesController = new AccessoriesController();

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
