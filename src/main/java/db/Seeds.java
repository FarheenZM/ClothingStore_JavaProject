package db;

import models.Category;
import models.Product;
import models.Shop;

public class Seeds {
//	public static void seedData() {
public static void main(String[] args) {

		DBHelper.deleteAll(Shop.class);
		DBHelper.deleteAll(Product.class);


		Shop ClothesShop = new Shop("ClothesShop");
		DBHelper.save(ClothesShop );

		Product clothing1 = new Product("Wide Leg Trousers", "Checked wool and mohair-blend wide-leg pants", Category.CLOTHES, 275, ClothesShop, "img1");
		DBHelper.save(clothing1);
		Product shoe1 = new Product("Lace Stilettos", "Portofino 105 lace sandals", Category.SHOES, 175, ClothesShop, "img2");
		DBHelper.save(shoe1);
		Product accessory1 = new Product("Embroidered cotton-twill baseball cap", " Made from black cotton-twill, this version is embroidered with 'Femme' on the front and has a Velcro®-fastening strap at the back. Style yours with one of the label's denim jackets.", Category.ACCESSORIES, 50, ClothesShop, "img3" );
		DBHelper.save(accessory1);
		Product newin1 = new Product("Wallace denim midi dress", "This belted 'Wallace' midi dress is cut from denim woven with a touch of stretch and traced with oversized gold buttons. Layer it over a turtleneck or T-shirt when the weather cools.", Category.NEW_IN, 300, ClothesShop, "img4" );
		DBHelper.save(newin1);

	}
}
