package controllers;
import db.DBHelper;
import models.Category;
import models.Product;
import models.Review;
import models.Shop;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class NewinController {
	public NewinController()

	{
		setupRoutes();
	}

	public void setupRoutes() {

		get("/manager/newin", (req, res) -> { //displays all products in the newin category
			HashMap<String, Object> model = new HashMap<>();

			List<Product> products = DBHelper.getAll(Product.class);
			List<Product> newProducts = new ArrayList<>();
			for (Product product : products) {
				if (product.getCategory() == Category.NEW_IN) {
					newProducts.add(product);
					model.put("template", "templates/products/newIn/index.vtl");
					model.put("newProducts", newProducts);
				}
			}
			return new ModelAndView(model, "templates/layout.vtl");

		}, new VelocityTemplateEngine());

		get("/manager/newin/:id", (req, res) -> { //displays one product
			HashMap<String, Object> model = new HashMap<>();
			String stringId = req.params(":id");
			Integer id = Integer.parseInt(stringId);
			Product product = DBHelper.find(id, Product.class);
			model.put("newin", product);
			model.put("template", "templates/products/newIn/show.vtl");
			return new ModelAndView(model, "templates/layout.vtl");

		}, new VelocityTemplateEngine());


		get("/manager/newin/:id/edit", (req,res) -> { //opens edit form
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

		post("/manager/newin/:id/edit", (req, res) -> {

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
			res.redirect("/manager/newin");
			return null;
		}, new VelocityTemplateEngine());


		post("/manager/newin/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
			Product newProduct = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
			DBHelper.delete(newProduct);
			res.redirect("/manager/newin");
			return null;
		}, new VelocityTemplateEngine());


	}
}