package com.analysis.shared.app.repo.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.analysis.shared.app.model.Cart;
import com.analysis.shared.app.model.CartProduct;
import com.analysis.shared.app.model.Product;
import com.analysis.shared.app.repo.ICartMgmtRepository;
import com.analysis.shared.app.repo.IProductMgmtRepository;

@Repository("cartMgmtRepo")
@Transactional
public class CartMgmtRepositoryImpl extends AbstractDAOImpl<Cart> implements ICartMgmtRepository {

	public CartMgmtRepositoryImpl() {
		super(Cart.class);
	}

	@Autowired
	private IProductMgmtRepository productMgmtRepo;

	@Override
	public void addCart(Cart cart) {
		save(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		update(cart);
	}

	@Override
	public void addProductInCart(CartProduct cProduct, String productName, String customerName) {
		@SuppressWarnings({ "deprecation", "rawtypes" })
		List cartIdList = getCurrentSession().createSQLQuery("select c.cart_id from Cart c where c.cart_name=:custName")
				.setParameter("custName", customerName).list();

		if (cartIdList.size() > 0) {
			System.out.println(cartIdList.get(0) + " | size : " + cartIdList.size());
			Cart cart = getById((String) cartIdList.get(0));
			Product product = productMgmtRepo.getProductByName(productName);
			cProduct.setProduct(product);
			cProduct.setCart(cart);
			cart.getCartProducts().add(cProduct);
			AbstractDAOImpl<CartProduct> cls= new AbstractDAOImpl<>(CartProduct.class);
			cls.setSessionFactory(getCurrentSession().getSessionFactory());
			cls.save(cProduct);
			
//			save(cart);
		}
	}

}
