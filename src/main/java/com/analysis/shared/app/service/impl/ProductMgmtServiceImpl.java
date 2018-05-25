package com.analysis.shared.app.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analysis.shared.app.model.Product;
import com.analysis.shared.app.repo.IProductMgmtRepository;
import com.analysis.shared.app.service.IProductMgmtService;

@Service("productMgmtService")
public class ProductMgmtServiceImpl implements IProductMgmtService {

	@Autowired
	private IProductMgmtRepository productMgmtRepo;

	@Override
	@Transactional
	public void initProductDatabase() {
		Product product = new Product();
		product.setName("Soap");
		product.setQty(12);
		Product product1 = new Product();
		product1.setName("Beeker");
		product1.setQty(10);
		productMgmtRepo.addProduct(product);
		productMgmtRepo.addProduct(product1);
	}
	

}
