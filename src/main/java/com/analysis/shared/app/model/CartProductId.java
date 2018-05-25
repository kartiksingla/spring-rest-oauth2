package com.analysis.shared.app.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CartProductId implements Serializable{
	
	private static final long serialVersionUID = 4098242875987307203L;

	@ManyToOne
	private Cart cart;
	
	@ManyToOne
	private Product product;

	public Cart getCart() {
		return cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartProductId other = (CartProductId) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
