package models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Table(name="shop")
public class Shop {

    private int id;
    private String name;
    private Set<Product> products;

	public Shop(){

	}

    public Shop(String name) {
        this.name = name;
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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
