package com.analysis.shared.app.repo.impl;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.shared.app.model.StoreEmployee;
import com.analysis.shared.app.repo.IEmployeeMgmtRepository;

@Repository//("empMgmtRepo")
public class EmployeeMgmtRepositoryImpl extends AbstractDAOImpl<StoreEmployee> implements IEmployeeMgmtRepository  {

	@Autowired
	private SessionFactory sessionFactory;
	
	public EmployeeMgmtRepositoryImpl() {
		super(StoreEmployee.class);
	}
	@Override
	@Transactional
	public void addEmployee(StoreEmployee emp) {
		sessionFactory.getCurrentSession().save(emp);
		emp.getDepartments().forEach(v -> {			
			sessionFactory.getCurrentSession().save(v);
		});
		sessionFactory.getCurrentSession().setHibernateFlushMode(FlushMode.ALWAYS);
	}
	@Override
	public StoreEmployee getEmployeeById(long id) {
		return getByFields("empId", id);
	}

}
