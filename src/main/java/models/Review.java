package models;

import javax.persistence.*;

@Entity
@Table(name="reviews")
public class Review {
	private int id;
	private String title;
	private String review;
	private Product product;

	public Review(){

	}

	public Review(String title, String review, Product product) {
		this.title = title;
		this.review = review;
		this.product = product;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(name="review")
	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

