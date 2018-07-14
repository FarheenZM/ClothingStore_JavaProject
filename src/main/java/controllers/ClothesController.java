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


public class ClothesController {


	public ClothesController() {
		setupRoutes();
	}

	public void setupRoutes() {

		get("/clothes", (req, res) -> { //displays all products of clothing category
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

		get("/clothes/:id", (req, res) -> { //displays one product
			HashMap<String, Object> model = new HashMap<>();
			String stringId = req.params(":id");
			Integer id = Integer.parseInt(stringId);
			Product product = DBHelper.find(id, Product.class);
			model.put("clothing", product);
			model.put("template", "templates/products/clothes/show.vtl");
			return new ModelAndView(model, "templates/layout.vtl");

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


		post("/clothes/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
			Product clothing = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
			DBHelper.delete(clothing);
			res.redirect("/clothes");
			return null;
		}, new VelocityTemplateEngine());
	}


}