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


public class AccessoriesController {

	public AccessoriesController()

		{ setupRoutes();
		}

		public void setupRoutes() {

		get("/manager/accessories", (req, res) -> { ////displays all products in the accessory category
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

		get("/manager/accessories/:id", (req, res) -> { //displays one product
			HashMap<String, Object> model = new HashMap<>();
			String stringId = req.params(":id");
			Integer id = Integer.parseInt(stringId);
			Product product = DBHelper.find(id, Product.class);
			model.put("accessory", product);
			model.put("template", "templates/products/accessories/show.vtl");
			return new ModelAndView(model, "templates/layout.vtl");

		}, new VelocityTemplateEngine());

		get("/manager/accessories/:id/edit", (req, res) -> { //opens edit form
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

		post("/manager/accessories/:id/edit", (req, res) -> {

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
			res.redirect("/manager/accessories");
			return null;
		}, new VelocityTemplateEngine());


		post("/manager/accessories/:id/delete", (req, res) -> { //deletes manager &  displays manager list ; only post route
			Product accessory = DBHelper.find(Integer.parseInt(req.params(":id")), Product.class);
			DBHelper.delete(accessory);
			res.redirect("/manager/accessories");
			return null;
		}, new VelocityTemplateEngine());


	}
}