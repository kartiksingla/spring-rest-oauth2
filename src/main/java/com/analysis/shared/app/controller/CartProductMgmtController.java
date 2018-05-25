package com.analysis.shared.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analysis.shared.app.service.ICartMgmtService;
import com.analysis.shared.app.service.IEmployeeMgmtService;
import com.analysis.shared.app.service.IProductMgmtService;

//@RestController
public class CartProductMgmtController {

	private static final Logger LOGGER = Logger.getLogger(CartProductMgmtController.class);

	@Autowired
	private ICartMgmtService cartMgmtService;
	
	@Autowired
	private IProductMgmtService productMgmtService;
	
//	@RequestMapping(value = "/initcpserver")
	public ResponseEntity<String> initDatabase() {
		String response = null;
		
		productMgmtService.initProductDatabase();
		
		cartMgmtService.initCartDatabase();
		cartMgmtService.initCartDatabase2();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
