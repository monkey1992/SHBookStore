package com.xiaoyu.shbookstore.protocol;

import com.xiaoyu.shbookstore.domain.Cart;

/**
 * 购物车--协议bean
 */
public class CartProtocal extends BaseProtocal {
	private Cart cart;
	private String response;
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
}
