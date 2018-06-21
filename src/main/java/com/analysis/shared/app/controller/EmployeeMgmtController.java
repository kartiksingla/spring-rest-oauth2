package com.analysis.shared.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.analysis.shared.app.model.Role;
import com.analysis.shared.app.model.StoreEmployee;
import com.analysis.shared.app.service.IEmployeeMgmtService;

@RestController
@RequestMapping("${root.employee}")  //("/employee")
public class EmployeeMgmtController {

	@Autowired
	private IEmployeeMgmtService empMgmtService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> hello() {
		String response = "hello mate!";
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StoreEmployee> getEmployeeById(@PathVariable(value = "id") int id) {
		StoreEmployee emp = empMgmtService.getEmployeeById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
}
