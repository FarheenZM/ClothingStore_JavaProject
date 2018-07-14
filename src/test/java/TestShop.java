import models.Category;
import models.Product;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestShop {

    Shop ClothesShop;
    Product product1;
    Product product2;

    @Before
    public void setUp() throws Exception {
       ClothesShop = new Shop("Tarheen");
       product1 = new Product("Shoe", "Black", Category.SHOES, 50, "img", ClothesShop);
       product2 = new Product("Belt", "Leather", Category.ACCESSORIES, 28, "img2", ClothesShop);
    }

    @Test
    public void hasName() {
        assertEquals("Tarheen", ClothesShop.getName());

        ClothesShop.setName("Style Journal");
        assertEquals("Style Journal", ClothesShop.getName());
    }

    @Test
    public void hasProducts() {
        assertEquals(0, ClothesShop.getProducts().size());

        Set<Product> products = new HashSet<>();
//        products.add(product1);
//        products.add(product2);
        Collections.addAll(products, product1, product2);

        ClothesShop.setProducts(products);
        assertEquals(2, ClothesShop.getProducts().size()); //Set has unique values, so duplicate products will give count 1

    }
}
