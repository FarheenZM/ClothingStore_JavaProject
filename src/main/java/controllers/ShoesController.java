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


public class ShoesController {


	public ShoesController() {
		setupRoutes();
	}

	public void setupRoutes() {


		get("/shoes", (req, res) -> { //displays all products of the shoe category
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


		post("/shoes/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
			Product shoe = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
			DBHelper.delete(shoe);
			res.redirect("/shoes");
			return null;
		}, new VelocityTemplateEngine());

	}

	}