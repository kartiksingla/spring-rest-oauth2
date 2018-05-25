package com.analysis.shared.app.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.analysis.shared.app.model.Role;
import com.analysis.shared.app.model.StoreEmployee;
import com.analysis.shared.app.repo.IEmployeeMgmtRepository;
import com.analysis.shared.app.service.ICartMgmtService;
import com.analysis.shared.app.service.IEmployeeMgmtService;
import com.analysis.shared.app.service.IProductMgmtService;

@Service("empMgmtService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService,UserDetailsService {

	@Autowired
	private IEmployeeMgmtRepository empMgmtRepo;
	@Autowired
	private ICartMgmtService cartMgmtService;

	@Autowired
	private IProductMgmtService productMgmtService;

	@Override
	public void initEmployeeInDatabase() {
		StoreEmployee emp = new StoreEmployee();
		emp.setName("Kartik");
		emp.setRole(Role.ROLE_USER);
		emp.setUname("ksingla");
		emp.setPassword("skartik");
		empMgmtRepo.addEmployee(emp);

		productMgmtService.initProductDatabase();

		cartMgmtService.initCartDatabase();
		cartMgmtService.initCartDatabase2();
	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User user = new User("ksingla", "$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		return user;
	}

}
