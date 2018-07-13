package models;

import javax.persistence.Column;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {

    private int id;
    private String title;
    private String description;
    private Category category;
    private int price;
    private String image;
    private Shop shop;

    public Product(){

    }

    public Product(String title, String description, Category category, int price, Shop shop, String image) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.image = image;
        this.shop = shop;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(value= EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name="price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToOne
	@JoinColumn(name="shop_id", nullable = false)
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
