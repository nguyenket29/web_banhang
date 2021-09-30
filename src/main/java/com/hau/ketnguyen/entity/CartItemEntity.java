package com.hau.ketnguyen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntity {
	@Column(name = "quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Transient
	public float getTotal() {
		return this.product.getPrice() * quantity;
	}
}
