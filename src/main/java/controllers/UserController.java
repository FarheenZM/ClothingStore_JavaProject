package controllers;

import db.DBHelper;
import db.DBUser;
import models.Product;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {


    public UserController(){
        setupRoutes();
    }

    public void setupRoutes() {
        get("/cart", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            User user = new User("username");
            DBHelper.save(user);
            List<Product> cartProducts = DBUser.getAllFavProducts(user);
            model.put("cartProducts", cartProducts);
            model.put("template", "templates/users/cart.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/cart/:id", (req, res) -> {

            User user = new User("username");

            Product favProduct = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);

            Set<Product> cartProducts = new HashSet<>();

            cartProducts.add(favProduct);
            user.setProducts(cartProducts);

            DBHelper.save(user);
            res.redirect("/cart");
            return null;

        }, new VelocityTemplateEngine());

    }
}
