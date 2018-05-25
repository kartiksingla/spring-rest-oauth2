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
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 176546447886409720L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(strategy = "org.hibernate.id.UUIDGenerator", name = "UUID")
	@Column(name = "product_id", updatable = false, nullable = false)
	private String id;

	@Column(name = "product_name", nullable = false)
	private String name;

	@Column(name = "product_qty", nullable = false)
	private int qty;

	@OneToMany(mappedBy = "pk.product")
	private Set<CartProduct> cartProduct = new HashSet<>();

	public Product() {
		this.id = UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}


}
