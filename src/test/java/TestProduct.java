import models.Category;
import models.Product;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProduct {

    Shop ClothesShop;
    Product product1;

    @Before
    public void setUp() throws Exception {
        ClothesShop = new Shop("Tarheen");
        product1 = new Product("Shoes", "Black", Category.SHOES, 50, "img", ClothesShop);
    }

    @Test
    public void hasTitle() {
        assertEquals("Shoes", product1.getTitle());

        product1.setTitle("Boots");
        assertEquals("Boots", product1.getTitle());
    }

    @Test
    public void hasDescription() {
        assertEquals("Black", product1.getDescription());

        product1.setDescription("Black & leather");
        assertEquals("Black & leather", product1.getDescription());
    }

    @Test
    public void hasCategory() {
        assertEquals(Category.SHOES, product1.getCategory());

        product1.setCategory(Category.NEW_IN);
        assertEquals(Category.NEW_IN, product1.getCategory());
    }

    @Test
    public void hasPrice() {
        assertEquals(50, product1.getPrice());

        product1.setPrice(45);
        assertEquals(45, product1.getPrice());
    }

    @Test
    public void hasImageUrl() {
        assertEquals("img", product1.getImage());
    }

    @Test
    public void hasShop() {
        assertEquals(ClothesShop, product1.getShop());
    }
}
