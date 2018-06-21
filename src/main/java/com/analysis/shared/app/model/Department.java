package com.analysis.shared.app.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "department")
public class Department implements Serializable{

	private static final long serialVersionUID = -5040765146728641272L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(strategy = "org.hibernate.id.UUIDGenerator", name = "UUID")
	@Column(name = "dept_id", updatable = false, nullable = false)
	private String id;
	
	@Column(name = "name", updatable = false, nullable = false)
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="e_id", nullable = false)//,updatable=true, insertable=true, nullable=true)
	private StoreEmployee employee;
	
	public StoreEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(StoreEmployee employee) {
		this.employee = employee;
	}
	
	public Department() {
		this.id = UUID.randomUUID().toString();
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

	
}
