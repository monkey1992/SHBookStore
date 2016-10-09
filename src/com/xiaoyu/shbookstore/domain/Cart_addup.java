package com.xiaoyu.shbookstore.domain;

/**
 * 购物车--商品总计bean
 * 
 * @author Administrator
 * 
 */
public class Cart_addup {
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
