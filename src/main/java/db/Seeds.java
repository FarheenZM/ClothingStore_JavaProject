package db;

import models.Category;
import models.Product;
import models.Review;
import models.Shop;

import java.util.List;

public class Seeds {
	public static void seedData() {
//public static void main(String[] args) {

		DBHelper.deleteAll(Shop.class);
		DBHelper.deleteAll(Product.class);


		Shop ClothesShop = new Shop("ClothesShop");
		DBHelper.save(ClothesShop );


		Product clothing1 = new Product("Wide Leg Trousers", "Checked wool and mohair-blend wide-leg pants", Category.CLOTHES, 275, "/images/Checked_pants.jpg", ClothesShop);
		DBHelper.save(clothing1);
		Product clothing2 = new Product("Kimi printed silk-crepon wrap midi dress", "This midi dress is ethically crafted from airy silk-crepon and hand-dyed by local artisans in a bright fuchsia leopard-print. The wrap silhouette means you can wear it fastened at the waist or open like a robe.", Category.CLOTHES, 200, "/images/Red_floral_midi_dress.jpg" , ClothesShop);
		DBHelper.save(clothing2);
		Product clothing3 = new Product("Paneled striped silk and chiffon dress", "Cut from weightless silk panels printed with terracotta and pink stripes, it has draped dolman sleeves and a pleated chiffon insert at one side.", Category.CLOTHES, 275, "/images/Silk_dress.jpg", ClothesShop);
		DBHelper.save(clothing3);
		Product clothing4 = new Product("Charron shirred gingham cotton-blend top", "Exactly the kind of style you would pack for a weekend getaway, this 'Charron' top is cut from gingham cotton-blend seersucker and shirred at the straps and bodice.", Category.CLOTHES, 115, "/images/Summer_top.jpg", ClothesShop);
		DBHelper.save(clothing4);



		Product shoe1 = new Product("Lace Stilettos", "Portofino 105 lace sandals", Category.SHOES, 175, "/images/Heels.jpg", ClothesShop);
		DBHelper.save(shoe1);
		Product shoe2 = new Product("Velvet Platform Sandals", "Crafted from plush velvet, this retro-inspired pair has a simple footstrap and chunky block heel that's balanced by a 40mm platform.", Category.SHOES, 225, "/images/Cuban_heel_boot.jpg", ClothesShop);
		DBHelper.save(shoe2);
		Product shoe3 = new Product("Callie croc-effect leather ankle boots", "Fitted with elasticated panels at the sides for flexibility, they're handcrafted from optic white croc-effect leather and detailed with sharp black stitching.", Category.SHOES, 255, "/images/Cuban_heel_boot.jpg", ClothesShop);
		DBHelper.save(shoe3);
		Product shoe4 = new Product("Floral-print textured-leather pumps", "Printed with roses inspired by a 19th century tapestry, this pair is decorated with a bow and paneled with shimmering gold at the block heel.", Category.SHOES, 285, "/images/Floral_heels.jpg", ClothesShop);
		DBHelper.save(shoe4);


		Product accessory1 = new Product("Embroidered cotton-twill baseball cap", " Made from black cotton-twill, this version is embroidered with 'Femme' on the front and has a Velcro®-fastening strap at the back. Style yours with one of the label's denim jackets.", Category.ACCESSORIES, 50, "/images/Cap.jpg", ClothesShop );
		DBHelper.save(accessory1);
		Product accessory2 = new Product("Gold-plated and enamel multi-stone earrings", "Inspired by the Victorian era, these celestial studs are cast from gold-plated sterling silver and dotted with pink tourmaline, blue topaz and peridot stones that complement the colors perfectly.", Category.ACCESSORIES, 125, "/images/Enamel_earrings.jpg", ClothesShop );
		DBHelper.save(accessory2);
		Product accessory3 = new Product("Gingham cotton, silver-tone, crystal and faux pearl choker", "This choker comprises a silver-tone chain and gingham ribbon inspired by the house's cult ballerina flats. A delicate hoop holds the two together, and it's strung with a crystal-encrusted sparrow charm and faux pearl drop at the center.\n", Category.ACCESSORIES, 75, "/images/Gingham_necklace.jpg", ClothesShop );
		DBHelper.save(accessory3);
		Product accessory4 = new Product("Whitney suede shoulder bag", "Made from plush light-blue suede, it’s fitted with a signature bracelet handle and has two flap compartments and internal slip pockets for your travel pass and cell.", Category.ACCESSORIES, 125, "/images/Blue_bag.jpg", ClothesShop );
		DBHelper.save(accessory4);


		Product newin1 = new Product("Wallace denim midi dress", "This belted 'Wallace' midi dress is cut from denim woven with a touch of stretch and traced with oversized gold buttons. Layer it over a turtleneck or T-shirt when the weather cools.", Category.NEW_IN, 300,"/images/Denim_dress.jpg", ClothesShop );
		DBHelper.save(newin1);
		Product newin2 = new Product("Cape-effect crepe gown", "It's been made in Italy from tomato-red crepe that skims the body and gently flares out to a floor-sweeping hem. The rippling cape-effect overlay creates dramatic movement as you leave the room.", Category.NEW_IN, 550,"/images/Cape_gown.jpg", ClothesShop );
		DBHelper.save(newin2);
		Product newin3 = new Product("Embellished stretch-crepe flared pants", "These stretch-crepe pants are embellished with staple hardware at the high front slits and fit slim through the leg before flaring out at the hem.", Category.NEW_IN, 215,"/images/Stitch_pants.jpg", ClothesShop );
		DBHelper.save(newin3);
		Product newin4 = new Product("Betty patent-leather platform sandals", "Made in Italy from glossy black patent-leather, this ‘70s-inspired pair has a supportive buckle-fastening ankle strap.", Category.NEW_IN, 155,"/images/Leather_heels.jpg", ClothesShop );
		DBHelper.save(newin4);

		List<Category> foundCategories = DBHelper.getAllCategories();

	}
}
