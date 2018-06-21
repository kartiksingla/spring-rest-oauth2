package com.analysis.shared.app.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "store_employee")
@XmlRootElement
public class StoreEmployee implements Serializable {

	private static final long serialVersionUID = 7673831920691055394L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(strategy = "org.hibernate.id.UUIDGenerator", name = "UUID")
	@Column(name = "e_id", updatable = false, nullable = false)
	@JsonIgnore
	private String id;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id", updatable = false, nullable = false)
	private long empId;

	@Column(name = "name", updatable = false, nullable = false)
	private String name;

	@Column(name = "username", updatable = false, nullable = false, unique = true)
	private String uname;

	@Column(name = "password", updatable = false, nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "role", updatable = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "employee")
	//@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	private Set<Department> departments;

//	public void addDepartment(Department department) {
//		departments.add(department);
//		department.setEmployee(this);
//	}
//
//	public void removeDepartment(Department department) {
//		departments.remove(department);
//		department.setEmployee(null);
//	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

//	public StoreEmployee() {
//		this.id = UUID.randomUUID().toString();
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = UUID.randomUUID().toString();
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
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
