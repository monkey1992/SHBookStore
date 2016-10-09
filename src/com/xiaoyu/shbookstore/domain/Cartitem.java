package com.xiaoyu.shbookstore.domain;

/**
 * 购物车--商品数量--商品信息封装bean
 * 
 * @author Administrator
 * 
 */
public class Cartitem {

	private Product product;

	private int prodNum;

	public Cartitem() {

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProdNum() {
		return prodNum;
	}

	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}

}
