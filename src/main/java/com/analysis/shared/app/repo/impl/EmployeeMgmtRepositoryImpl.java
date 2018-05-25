package com.analysis.shared.app.repo.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.analysis.shared.app.model.StoreEmployee;
import com.analysis.shared.app.repo.IEmployeeMgmtRepository;

@Repository("empMgmtRepo")
@Transactional
public class EmployeeMgmtRepositoryImpl extends AbstractDAOImpl<StoreEmployee> implements IEmployeeMgmtRepository  {

	@Autowired
	private SessionFactory sessionFactory;
	
	public EmployeeMgmtRepositoryImpl() {
		super(StoreEmployee.class);
	}
	@Override
	public void addEmployee(StoreEmployee emp) {
		saveOrUpdate(emp);
	}

}
