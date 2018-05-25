package com.analysis.shared.app.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "store_employee")
public class StoreEmployee implements Serializable {

	private static final long serialVersionUID = 7673831920691055394L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(strategy = "org.hibernate.id.UUIDGenerator", name = "UUID")
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(name = "name", updatable = false, nullable = false)
	private String name;

	@Column(name = "username", updatable = false, nullable = false, unique = true)
	private String uname;

	@Column(name = "password", updatable = false, nullable = false)
	private String password;

	@Column(name = "role", updatable = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public StoreEmployee() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUname() {
		return uname;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
