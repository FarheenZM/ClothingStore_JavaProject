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


        get("/accessories/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("accessory", product);
            model.put("template", "templates/products/accessories/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/clothes/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("clothing", product);
            model.put("template", "templates/products/clothes/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/newin/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("newin", product);
            model.put("template", "templates/products/newIn/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/shoes/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("shoe", product);
            model.put("template", "templates/products/shoes/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/shoes/:id/edit", (req,res) -> { //opens edit form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            List<Category> categories = DBHelper.getAllCategories();
            model.put("categories", categories);
            model.put("shoe", product);
            model.put("template", "templates/products/shoes/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/shoes/:id/edit", (req, res) -> {

            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            int price = Integer.parseInt(req.queryParams("price"));
            String image = req.queryParams("image");
            List<Shop> shops = DBHelper.getAll(Shop.class);
            Shop shop = shops.get(0);

            Product shoe = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            shoe.setTitle(title);
            shoe.setDescription(description);
            shoe.setCategory(category);
            shoe.setPrice(price);
            shoe.setImage(image);
            shoe.setShop(shop);

            DBHelper.save(shoe);
            res.redirect("/shoes");
            return null;
        }, new VelocityTemplateEngine());

        get("/newin/:id/edit", (req,res) -> { //opens edit form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            List<Category> categories = DBHelper.getAllCategories();
            model.put("categories", categories);
            model.put("newProduct", product);
            model.put("template", "templates/products/newIn/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/newin/:id/edit", (req, res) -> {

            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            int price = Integer.parseInt(req.queryParams("price"));
            String image = req.queryParams("image");
            List<Shop> shops = DBHelper.getAll(Shop.class);
            Shop shop = shops.get(0);

            Product newProduct = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            newProduct.setTitle(title);
            newProduct.setDescription(description);
            newProduct.setCategory(category);
            newProduct.setPrice(price);
            newProduct.setImage(image);
            newProduct.setShop(shop);

            DBHelper.save(newProduct);
            res.redirect("/newin");
            return null;
        }, new VelocityTemplateEngine());

        get("/clothes/:id/edit", (req,res) -> { //opens edit form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            List<Category> categories = DBHelper.getAllCategories();
            model.put("categories", categories);
            model.put("clothing", product);
            model.put("template", "templates/products/clothes/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/clothes/:id/edit", (req, res) -> {

            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            int price = Integer.parseInt(req.queryParams("price"));
            String image = req.queryParams("image");
            List<Shop> shops = DBHelper.getAll(Shop.class);
            Shop shop = shops.get(0);

            Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            clothing.setTitle(title);
            clothing.setDescription(description);
            clothing.setCategory(category);
            clothing.setPrice(price);
            clothing.setImage(image);
            clothing.setShop(shop);

            DBHelper.save(clothing);
            res.redirect("/clothes");
            return null;
        }, new VelocityTemplateEngine());



        get("/accessories/:id/edit", (req,res) -> { //opens edit form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            List<Category> categories = DBHelper.getAllCategories();
            model.put("categories", categories);
            model.put("accessory", product);
            model.put("template", "templates/products/accessories/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/accessories/:id/edit", (req, res) -> {

            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            int price = Integer.parseInt(req.queryParams("price"));
            String image = req.queryParams("image");
            List<Shop> shops = DBHelper.getAll(Shop.class);
            Shop shop = shops.get(0);

            Product accessory = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            accessory.setTitle(title);
            accessory.setDescription(description);
            accessory.setCategory(category);
            accessory.setPrice(price);
            accessory.setImage(image);
            accessory.setShop(shop);

            DBHelper.save(accessory);
            res.redirect("/accessories");
            return null;
        }, new VelocityTemplateEngine());


        post("/accessories/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
            Product accessory = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
            DBHelper.delete(accessory);
            res.redirect("/accessories");
            return null;
        }, new VelocityTemplateEngine());

        post("/clothes/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
            Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
            DBHelper.delete(clothing);
            res.redirect("/clothes");
            return null;
        }, new VelocityTemplateEngine());

        post("/newin/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
            Product newProduct = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
            DBHelper.delete(newProduct);
            res.redirect("/newin");
            return null;
        }, new VelocityTemplateEngine());

        post("/shoes/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
            Product shoe = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
            DBHelper.delete(shoe);
            res.redirect("/shoes");
            return null;
        }, new VelocityTemplateEngine());




    }



}
