package com.analysis.shared.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.analysis.shared.app.service.IEmployeeMgmtService;

@RestController
@RequestMapping("/users")
public class EmployeeMgmtController {

	private static final Logger LOGGER = Logger.getLogger(EmployeeMgmtController.class);

	@Autowired
	private IEmployeeMgmtService empMgmtService;
	
	@RequestMapping(value = "/initserver")
	public ResponseEntity<String> initDatabase() {
		String response = null;
		empMgmtService.initEmployeeInDatabase();
//		cartProductMgmtController.initDatabase();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> hello() {
		String response = "hello mate!";
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
