package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.Column;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Review> reviews;
    private Set<User> users;

    public Product(){

    }

    public Product(String title, String description, Category category, int price, String image, Shop shop) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.image = image;
        this.shop = shop;
        this.users = new HashSet<>();
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
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }


    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "products_users",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
