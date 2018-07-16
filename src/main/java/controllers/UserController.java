package controllers;

import db.DBHelper;
import db.DBUser;
import models.Category;
import models.Product;
import models.Review;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {


    public UserController(){
        setupRoutes();
    }

    public void setupRoutes() {

        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/homeUser.vtl");
            return new ModelAndView(model, "templates/layoutUser.vtl");
        }, new VelocityTemplateEngine());


        get("/accessories", (req, res) -> { ////displays all products in the accessory category
            HashMap<String, Object> model = new HashMap<>();
            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> accessories = new ArrayList<>();
            for( Product product : products){
                if (product.getCategory() == Category.ACCESSORIES){
                    accessories.add(product);
                    model.put("template", "templates/users/accessories/index.vtl");
                    model.put("accessories", accessories);
                }
            }
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());

        get("/accessories/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("accessory", product);
            model.put("template", "templates/users/accessories/show.vtl");
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());




        get("/clothes", (req, res) -> { //displays all products of clothing category
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> clothes = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory() == Category.CLOTHES) {
                    clothes.add(product);
                    model.put("template", "templates/users/clothes/index.vtl");
                    model.put("clothes", clothes);
                }
            }
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());

        get("/clothes/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            Set<Review> reviews = product.getReviews();
            model.put("reviews", reviews);
            model.put("clothing", product);
            model.put("template", "templates/users/clothes/show.vtl");
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());


        get("/newin", (req, res) -> { //displays all products in the newin category
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> newProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory() == Category.NEW_IN) {
                    newProducts.add(product);
                    model.put("template", "templates/users/newIn/index.vtl");
                    model.put("newProducts", newProducts);
                }
            }
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());

        get("/newin/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("newin", product);
            model.put("template", "templates/users/newIn/show.vtl");
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());




        get("/shoes", (req, res) -> { //displays all products of the shoe category
            HashMap<String, Object> model = new HashMap<>();

            List<Product> products = DBHelper.getAll(Product.class);
            List<Product> shoes = new ArrayList<>();
            for( Product product : products) {
                if (product.getCategory() == Category.SHOES) {
                    shoes.add(product);
                    model.put("template", "templates/users/shoes/index.vtl");
                    model.put("shoes", shoes);
                }
            }
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());


        get("/shoes/:id", (req, res) -> { //displays one product
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);
            model.put("shoe", product);
            model.put("template", "templates/users/shoes/show.vtl");
            return new ModelAndView(model, "templates/layoutUser.vtl");

        }, new VelocityTemplateEngine());



        get("/cart", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            User foundUser = DBHelper.find(1, User.class);

            List<Product> cartProducts = DBUser.getAllFavProducts(foundUser);

            model.put("cartProducts", cartProducts);
            model.put("template", "templates/users/cart.vtl");
            return new ModelAndView(model, "templates/layoutUser.vtl");
        }, new VelocityTemplateEngine());


        post("/cart/:id", (req, res) -> {

           User foundUser = DBHelper.find(1, User.class);
            Product favProduct = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            List<Product> cartProducts = DBUser.getAllFavProducts(foundUser);
            cartProducts.add(favProduct);
            foundUser.setProducts(cartProducts);

            DBHelper.save(foundUser);
            res.redirect("/cart");
            return null;

        }, new VelocityTemplateEngine());

        get("/accessories/:id/review", (req, res) -> { //opens up the review form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);

            model.put("accessory", product);
            model.put("template", "templates/users/accessories/show.vtl");
            return new ModelAndView(model, "templates/layoutUser");
        }, new VelocityTemplateEngine());


        post("/accessories/:id/review", (req, res) -> { //saves a review to a product
            String title = req.queryParams("title");
            String review = req.queryParams("review");
            Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            Set<Review> reviews = new HashSet<>();
            Review newReview = new Review(title, review, clothing);
            DBHelper.save(newReview);

            reviews.add(newReview);
            clothing.setReviews(reviews);

            DBHelper.save(clothing);
            res.redirect("/accessories");
            return null;
        }, new VelocityTemplateEngine());

        get("/clothes/:id/review", (req, res) -> { //opens up the review form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);

            model.put("clothing", product);
            model.put("template", "templates/users/clothes/show.vtl");
            return new ModelAndView(model, "templates/layoutUser");
        }, new VelocityTemplateEngine());


        post("/clothes/:id/review", (req, res) -> { //saves a review to a product
            String title = req.queryParams("title");
            String review = req.queryParams("review");
            Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            Set<Review> reviews = new HashSet<>();
            Review newReview = new Review(title, review, clothing);
            DBHelper.save(newReview);

            reviews.add(newReview);
            clothing.setReviews(reviews);

            DBHelper.save(clothing);
            res.redirect("/clothes");
            return null;
        }, new VelocityTemplateEngine());


        get("/newin/:id/review", (req, res) -> { //opens up the review form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);

            model.put("newin", product);
            model.put("template", "templates/users/newIn/show.vtl");
            return new ModelAndView(model, "templates/layoutUser");
        }, new VelocityTemplateEngine());


        post("/newin/:id/review", (req, res) -> { //saves a review to a product
            String title = req.queryParams("title");
            String review = req.queryParams("review");
            Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            Set<Review> reviews = new HashSet<>();
            Review newReview = new Review(title, review, clothing);
            DBHelper.save(newReview);

            reviews.add(newReview);
            clothing.setReviews(reviews);

            DBHelper.save(clothing);
            res.redirect("/newin");
            return null;
        }, new VelocityTemplateEngine());


        get("/shoes/:id/review", (req, res) -> { //opens up the review form
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer id = Integer.parseInt(stringId);
            Product product = DBHelper.find(id, Product.class);

            model.put("shoe", product);
            model.put("template", "templates/users/shoes/show.vtl");
            return new ModelAndView(model, "templates/layoutUser");
        }, new VelocityTemplateEngine());


        post("/shoes/:id/review", (req, res) -> { //saves a review to a product
            String title = req.queryParams("title");
            String review = req.queryParams("review");
            Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            Set<Review> reviews = new HashSet<>();
            Review newReview = new Review(title, review, clothing);
            DBHelper.save(newReview);

            reviews.add(newReview);
            clothing.setReviews(reviews);

            DBHelper.save(clothing);
            res.redirect("/shoes");
            return null;
        }, new VelocityTemplateEngine());


    }
}
