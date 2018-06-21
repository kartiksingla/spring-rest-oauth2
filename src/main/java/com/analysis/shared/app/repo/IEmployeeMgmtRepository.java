package com.analysis.shared.app.repo;

import com.analysis.shared.app.model.StoreEmployee;

public interface IEmployeeMgmtRepository {

	void addEmployee(StoreEmployee emp);

	StoreEmployee getEmployeeById(long id);

}
