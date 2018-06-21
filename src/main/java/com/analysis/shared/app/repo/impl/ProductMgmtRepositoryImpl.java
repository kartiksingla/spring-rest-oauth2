package com.analysis.shared.app.repo.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.analysis.shared.app.model.Product;
import com.analysis.shared.app.repo.IProductMgmtRepository;

@Repository("productMgmtRepo")
@Transactional
public class ProductMgmtRepositoryImpl extends AbstractDAOImpl<Product> implements IProductMgmtRepository  {

	public ProductMgmtRepositoryImpl() {
		super(Product.class);
	}

	@Override
	public void addProduct(Product product) {
		save(product);
	}

	@Override
	public void updateProduct(Product product) {
		update(product);
	}

	@Override
	public Product getProductByName(String productName) {
		String id = (String) getCurrentSession().createSQLQuery("select c.product_id from Product c where c.product_name=:productName").setParameter("productName",productName).list().get(0);
		return getById(id);
	}


}
