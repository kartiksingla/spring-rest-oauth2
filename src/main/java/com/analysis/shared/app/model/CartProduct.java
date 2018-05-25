package com.analysis.shared.app.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="cart_product")
@AssociationOverrides({
	@AssociationOverride(name="pk.cart", joinColumns = {
			@JoinColumn(name = "cart_id")
	}),
	@AssociationOverride(name="pk.product", joinColumns = {
			@JoinColumn(name = "product_id")
	})
})
public class CartProduct implements Serializable {

	private static final long serialVersionUID = 3920823142575008734L;

	@EmbeddedId
	private CartProductId pk = new CartProductId();

	@Column(name = "p_qty")
	private int cartProductQty;

	public CartProduct() {

	}

	public CartProductId getPk() {
		return pk;
	}

	public int getCartProductQty() {
		return cartProductQty;
	}

	public void setPk(CartProductId pk) {
		this.pk = pk;
	}

	public void setCartProductQty(int cartProductQty) {
		this.cartProductQty = cartProductQty;
	}
	
	public void setProduct(Product product){
		getPk().setProduct(product);
	}
	
	public void setCart(Cart product){
		getPk().setCart(product);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartProductQty;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		CartProduct other = (CartProduct) obj;
		if (cartProductQty != other.cartProductQty)
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
}
