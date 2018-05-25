package com.analysis.shared.app.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analysis.shared.app.model.Cart;
import com.analysis.shared.app.model.CartProduct;
import com.analysis.shared.app.repo.ICartMgmtRepository;
import com.analysis.shared.app.service.ICartMgmtService;
import com.analysis.shared.app.service.IEmployeeMgmtService;
import com.analysis.shared.app.service.IProductMgmtService;

@Service("cartMgmtService")
public class CartMgmtServiceImpl implements ICartMgmtService {

	@Autowired
	private ICartMgmtRepository cartMgmtRepo;
	
	@Override
	@Transactional
	public void initCartDatabase() {
		Cart cart = new Cart();
		String custName = "Customer_ID_1";
		cart.setName(custName);
		cartMgmtRepo.addCart(cart);
		CartProduct cProduct = new CartProduct();
		cProduct.setCartProductQty(2);
		
//		cartMgmtRepo.addProductInCart(cProduct,"Soap","Customer_ID_1");
	}

	@Override
	@Transactional
	public void initCartDatabase2() {
		CartProduct cProduct = new CartProduct();
		cProduct.setCartProductQty(2);
		
		cartMgmtRepo.addProductInCart(cProduct,"Soap","Customer_ID_1");
	}

}
