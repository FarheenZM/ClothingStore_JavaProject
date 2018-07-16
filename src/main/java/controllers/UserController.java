package controllers;

import db.DBHelper;
import db.DBUser;
import models.Product;
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
        get("/cart", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            User foundUser = DBHelper.find(1, User.class);

            List<Product> cartProducts = DBUser.getAllFavProducts(foundUser);

            model.put("cartProducts", cartProducts);
            model.put("template", "templates/users/cart.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
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

    }
}
