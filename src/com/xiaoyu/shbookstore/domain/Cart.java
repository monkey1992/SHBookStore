package com.xiaoyu.shbookstore.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车--bean
 * 
 * @author Administrator
 * 
 */
public class Cart {
	
	private Cart(){}
	private static Cart cart = new Cart();
	private ArrayList<Product> product = new ArrayList<Product>();
	/**
	 * 单例获取方法
	 * @return
	 */
	public static Cart getCart(){
		return cart;
	}
	/**
	 * 获取商品列表
	 * @return
	 */
	public ArrayList<Product> getProduct() {
		return product;
	}
	/**
	 * 设置商品列表
	 * @param product
	 */
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}

	/**
	 * 促销消息
	 */
	private List<String> prom;
	
	/**
	 * 商品列表
	 */
	private List<Product> productlist;
	
	/**
	 * 商品数量
	 */
	private Cartitem item;
	
	/**
	 * 购物车数量
	 */
	private List<Cartitem> cartitem;
	
	/**
	 * 商品总计
	 */
	private Cart_addup cart_addup;
	/**
	 * 商品数量总计
	 */
	private String totalCount;
	/**
	 * 商品金额总计
	 */
	private String totalPrice;
	/**
	 * 商品积分总计
	 */
	private String totalPoint;
	
	public List<String> getProm() {
		return prom;
	}
	
	public void setProm(List<String> prom) {
		this.prom = prom;
	}
	
	public Cartitem getItem() {
		return item;
	}
	
	public void setItem(Cartitem item) {
		this.item = item;
	}
	
	public List<Cartitem> getCartitem() {
		return cartitem;
	}
	
	public void setCartitem(List<Cartitem> cartitem) {
		this.cartitem = cartitem;
	}
	
	public String getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getTotalPoint() {
		return totalPoint;
	}
	
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
	
}
