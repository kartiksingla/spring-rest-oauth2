package com.analysis.shared.app.service;

import com.analysis.shared.app.model.StoreEmployee;

public interface IEmployeeMgmtService {

	void initEmployeeInDatabase();

	StoreEmployee getEmployeeById(long id);
}
