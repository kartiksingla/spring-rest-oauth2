package com.analysis.shared.app.repo;

import com.analysis.shared.app.model.Product;

public interface IProductMgmtRepository {

	public void addProduct(Product product);

	public void updateProduct(Product product);

	public Product getProductByName(String productName);
}
