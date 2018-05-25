package com.analysis.shared.app.repo;

import com.analysis.shared.app.model.Cart;
import com.analysis.shared.app.model.CartProduct;

public interface ICartMgmtRepository {

	void addCart(Cart cart);

	void updateCart(Cart cart);

	void addProductInCart(CartProduct cProduct, String string, String string2);

}
