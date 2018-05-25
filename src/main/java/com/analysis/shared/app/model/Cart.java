package com.analysis.shared.app.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
	
	private static final long serialVersionUID = 176546447886409720L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(strategy = "org.hibernate.id.UUIDGenerator", name = "UUID")
	@Column(name = "cart_id", updatable = false, nullable = false)
	private String id;

	@Column(name = "cart_name", nullable = false)
	private String name;

	@OneToMany(mappedBy ="pk.cart")
	private Set<CartProduct> cartProducts = new HashSet<>();
	
	public Cart() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(Set<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}

}
