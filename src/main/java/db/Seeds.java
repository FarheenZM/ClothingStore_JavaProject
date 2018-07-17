package db;

import models.*;

import java.util.List;

public class Seeds {
    public static void seedData() {
//public static void main(String[] args) {

        DBHelper.deleteAll(Shop.class);
        DBHelper.deleteAll(Product.class);


        Shop ClothesShop = new Shop("ClothesShop");
        DBHelper.save(ClothesShop);

        User user = new User("Jane");
        DBHelper.save(user);


        Product clothing1 = new Product("Wide Leg Trousers", "Checked wool and mohair-blend wide-leg pants", Category.CLOTHES, 275, "/images/Checked_pants.jpg", ClothesShop);
        DBHelper.save(clothing1);
        Product clothing2 = new Product("Kimi printed silk-crepon wrap midi dress", "This midi dress is ethically crafted from airy silk-crepon and hand-dyed by local artisans in a bright fuchsia leopard-print. The wrap silhouette means you can wear it fastened at the waist or open like a robe.", Category.CLOTHES, 200, "/images/Red_floral_midi_dress.jpg", ClothesShop);
        DBHelper.save(clothing2);
        Product clothing3 = new Product("Paneled striped silk and chiffon dress", "Cut from weightless silk panels printed with terracotta and pink stripes, it has draped dolman sleeves and a pleated chiffon insert at one side.", Category.CLOTHES, 275, "/images/Silk_dress.jpg", ClothesShop);
        DBHelper.save(clothing3);
        Product clothing4 = new Product("Charron shirred gingham cotton-blend top", "Exactly the kind of style you would pack for a weekend getaway, this 'Charron' top is cut from gingham cotton-blend seersucker and shirred at the straps and bodice.", Category.CLOTHES, 115, "/images/Summer_top.jpg", ClothesShop);
        DBHelper.save(clothing4);
        Product clothing5 = new Product("Embroidered guipure lace and tulle gown", "This lace gown has carefully placed crocheted panels that follow the natural lines of your silhouette. This piece is perfect for fall weddings and balls.", Category.CLOTHES, 425, "/images/Red_lace_gown.jpg", ClothesShop);
        DBHelper.save(clothing5);
        Product clothing6 = new Product("Jacquard-knit mini dress", "this jacquard-knit dress. Made in Italy, it's shaped with a nipped-in waist and a flattering skirt that falls into soft folds.", Category.CLOTHES, 310, "/images/Jacquard_mini_dress.jpg", ClothesShop);
        DBHelper.save(clothing6);
        Product clothing7 = new Product("Camilla printed satin-twill dress", "This 'Camilla' dress is cut from lustrous satin-twill for a loose fit and has drawstring ties to define your waist. ", Category.CLOTHES, 280, "/images/Printed_satin_dress.jpg", ClothesShop);
        DBHelper.save(clothing7);
        Product clothing8 = new Product("One-shoulder embroidered silk-organza top", "Made from elegant layers of silk-organza, this top is embroidered with black and cobalt-blue florals and trimmed with a ruffle at the one-shoulder neckline, before falling to a peplum hem. ", Category.CLOTHES, 110, "/images/One_shoulder_top.jpg", ClothesShop);
        DBHelper.save(clothing8);
        Product clothing9 = new Product("Cutout corded lace blouse", "Made from delicate black lace with a beige tulle underlay for total coverage. It's detailed with a keyhole cutout at the front and and has a velvet trim to accentuate your waist.", Category.CLOTHES, 100, "/images/Lace_blouse.jpg", ClothesShop);
        DBHelper.save(clothing9);


        Product shoe1 = new Product("Lace Stilettos", "Portofino 105 lace sandals", Category.SHOES, 175, "/images/Heels.jpg", ClothesShop);
        DBHelper.save(shoe1);
        Product shoe2 = new Product("Velvet Platform Sandals", "Crafted from plush velvet, this retro-inspired pair has a simple footstrap and chunky block heel that's balanced by a 40mm platform.", Category.SHOES, 225, "/images/Leather_heels.jpg", ClothesShop);
        DBHelper.save(shoe2);
        Product shoe3 = new Product("Callie croc-effect leather ankle boots", "Fitted with elasticated panels at the sides for flexibility, they're handcrafted from optic white croc-effect leather and detailed with sharp black stitching.", Category.SHOES, 255, "/images/Cuban_heel_boot.jpg", ClothesShop);
        DBHelper.save(shoe3);
        Product shoe4 = new Product("Floral-print textured-leather pumps", "Printed with roses inspired by a 19th century tapestry, this pair is decorated with a bow and paneled with shimmering gold at the block heel.", Category.SHOES, 285, "/images/Floral_heels.jpg", ClothesShop);
        DBHelper.save(shoe4);
        Product shoe5 = new Product("The Rockstud metallic textured-leather pumps", "Crafted in Italy from supple textured-leather, these pale-gold pumps are punctuated with gleaming pyramid studs", Category.SHOES, 205, "/images/Pale_gold_pumps.jpg", ClothesShop);
        DBHelper.save(shoe5);
        Product shoe6 = new Product("Ginger embellished two-tone textured-leather pumps", "Made from textured-leather in a red and midnight-blue colorway that resembles the house's iconic striped webbing. Set on a block heel, this square-toe pair has a loafer silhouette and is topped with a gold tiger's head buckle. ", Category.SHOES, 185, "/images/Red_shoes.jpg", ClothesShop);
        DBHelper.save(shoe6);
        Product shoe7 = new Product("Almeria leather wedge sandals", "Made exclusively for us from tan leather, this pair is set on a sky-high, jute-trimmed wedge heel that's balanced by a sturdy platform. ", Category.SHOES, 110, "/images/Wedge_sandals.jpg", ClothesShop);
        DBHelper.save(shoe7);
        Product shoe8 = new Product("Glittered leather pumps", "They have been made in Italy from supple leather covered in shimmering gold glitter. The 65mm heel is fluted at the top for a comfortable step.", Category.SHOES, 200, "/images/Glittered_pumps.jpg", ClothesShop);
        DBHelper.save(shoe8);
        Product shoe9 = new Product("Rylee snake-effect leather ankle boots", "This Western-inspired pair has been made in Italy from mustard and black snake-effect leather", Category.SHOES, 150, "/images/Snake_effect_boots.jpg", ClothesShop);
        DBHelper.save(shoe9);


        Product accessory1 = new Product("Embroidered cotton-twill baseball cap", " Made from black cotton-twill, this version is embroidered with 'Femme' on the front and has a Velcro®-fastening strap at the back. Style yours with one of the label's denim jackets.", Category.ACCESSORIES, 50, "/images/Cap.jpg", ClothesShop);
        DBHelper.save(accessory1);
        Product accessory2 = new Product("Gold-plated and enamel multi-stone earrings", "Inspired by the Victorian era, these celestial studs are cast from gold-plated sterling silver and dotted with pink tourmaline, blue topaz and peridot stones that complement the colors perfectly.", Category.ACCESSORIES, 125, "/images/Enamel_earrings.jpg", ClothesShop);
        DBHelper.save(accessory2);
        Product accessory3 = new Product("Gingham cotton, silver-tone, crystal and faux pearl choker", "This choker comprises a silver-tone chain and gingham ribbon inspired by the house's cult ballerina flats. A delicate hoop holds the two together, and it's strung with a crystal-encrusted sparrow charm and faux pearl drop at the center.\n", Category.ACCESSORIES, 75, "/images/Gingham_necklace.jpg", ClothesShop);
        DBHelper.save(accessory3);
        Product accessory4 = new Product("Whitney suede shoulder bag", "Made from plush light-blue suede, it’s fitted with a signature bracelet handle and has two flap compartments and internal slip pockets for your travel pass and cell.", Category.ACCESSORIES, 125, "/images/Blue_bag.jpg", ClothesShop);
        DBHelper.save(accessory4);
        Product accessory5 = new Product("Leather belt", "This leather version has a classic width that will work well with jeans and secures with a sleek gold square buckle. ", Category.ACCESSORIES, 75, "/images/Black_leather_belt.jpg", ClothesShop);
        DBHelper.save(accessory5);
        Product accessory6 = new Product("The Breaker cat-eye acetate sunglasses", "These black acetate sunglasses have a slim profile and exaggerated cat-eye frames with squared-off tips. ", Category.ACCESSORIES, 90, "/images/Black_sunglasses.jpg", ClothesShop);
        DBHelper.save(accessory6);
        Product accessory7 = new Product("Croc-effect leather belt", "This style is made from croc-effect leather and hand-polished using natural wax. The tan hue works particularly with classic neutrals or indigo denim.", Category.ACCESSORIES, 70, "/images/Croc_effect_belt.jpg", ClothesShop);
        DBHelper.save(accessory7);
        Product accessory8 = new Product("Oval-frame gunmetal-tone sunglasses", "Created in a gunmetal-tone colorway, this pair has a '90s-inspired design and mirrored oval-shaped lenses with full UV protection. ", Category.ACCESSORIES, 125, "/images/Blue_sunglasses.jpg", ClothesShop);
        DBHelper.save(accessory8);
        Product accessory9 = new Product("Faye medium glossed-leather shoulder bag", "The ombré sfumato effect on Chloé's 'Faye' bag is achieved by applying three different coats to meticulously shade the leather in gradations.", Category.ACCESSORIES, 225, "/images/Red_bag.jpg", ClothesShop);
        DBHelper.save(accessory9);


        Product newin1 = new Product("Wallace denim midi dress", "This belted 'Wallace' midi dress is cut from denim woven with a touch of stretch and traced with oversized gold buttons. Layer it over a turtleneck or T-shirt when the weather cools.", Category.NEW_IN, 300, "/images/Denim_dress.jpg", ClothesShop);
        DBHelper.save(newin1);
        Product newin2 = new Product("Cape-effect crepe gown", "It's been made in Italy from tomato-red crepe that skims the body and gently flares out to a floor-sweeping hem. The rippling cape-effect overlay creates dramatic movement as you leave the room.", Category.NEW_IN, 550, "/images/Cape_gown.jpg", ClothesShop);
        DBHelper.save(newin2);
        Product newin3 = new Product("Embellished stretch-crepe flared pants", "These stretch-crepe pants are embellished with staple hardware at the high front slits and fit slim through the leg before flaring out at the hem.", Category.NEW_IN, 215, "/images/Stitch_pants.jpg", ClothesShop);
        DBHelper.save(newin3);
        Product newin4 = new Product("Betty patent-leather platform sandals", "Made in Italy from glossy black patent-leather, this ‘70s-inspired pair has a supportive buckle-fastening ankle strap.", Category.NEW_IN, 155, "/images/Leather_heels.jpg", ClothesShop);
        DBHelper.save(newin4);

        Product newin5 = new Product("Printed georgette mini dress", "The dress is emblazoned with the French house's signature 'Blossom' print - the abstract flowers and evil eye motif have a very '70s feel. It's cut from fluttery georgette with billowing bell sleeves and ties at the waist to temper the loose fit. ", Category.NEW_IN, 218, "/images/Georgette_mini_dress.jpg", ClothesShop);
        DBHelper.save(newin5);
        Product newin6 = new Product("Hortensia mini color-block leather shoulder bag", "Made from panels of smooth leather, it’s color-blocked in black, chestnut and white hues that will complement any outfit. .", Category.NEW_IN, 115, "/images/Color_block_bag.jpg", ClothesShop);
        DBHelper.save(newin6);
        Product newin7 = new Product("High-rise wide-leg jeans", "This wide-leg jeans are cut from rigid denim in a pale wash the brand calls 'Light Garage Indigo' - there's a lighter panel running down the sides so they look like vintage pairs pieced together. ", Category.NEW_IN, 120, "/images/Wide_leg_jeans.jpg", ClothesShop);
        DBHelper.save(newin7);
        Product newin8 = new Product("Gathered silk crepe de chine blouse", "This cornflower-blue blouse is deftly gathered around the neck and sleeves - both of which tie with pretty bows. The loose fit makes it perfect for tucking into high-waisted pants or a skirt.\n", Category.NEW_IN, 80, "/images/Cornflower_blue_blouse.jpg", ClothesShop);
        DBHelper.save(newin8);
        Product newin9 = new Product("Leopard-print silk-chiffon blouse", "It's cut from lightweight silk-chiffon and can be worn with the ruffled yoke facing to the front or back. Style it with tailored pants or a skirt at the office. ", Category.NEW_IN, 110, "/images/Leopard_print.jpg", ClothesShop);
        DBHelper.save(newin9);

        List<Category> foundCategories = DBHelper.getAllCategories();

    }
}
