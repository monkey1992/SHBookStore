package com.xiaoyu.shbookstore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 商品信息封装
 */
public class Product implements Serializable{

	/**
	 * 商品ID
	 */
	private int id;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品图片URL
	 */
	private String pic;


	/**
	 * 商品售价
	 */
	private String price;

	/**
	 * 商品数量，0为缺货或下架
	 */
	private int number;

	/**
	 * 商品购买数量上限
	 */
	private int uplimit;

	/**
	 * 是否赠品
	 */
	private boolean isgift;
	/**
	 * 市场价
	 */
	private String marketPrice;
	
	/**
	 * 评论数量
	 */
	private String commentCount;
	
	/**
	 * 商品总价
	 */
	private String totalPrice;
	
	/**
	 * 商品购买的数量 
	 */
	private String prodAccount;
	
	/**
	 * 在商品详情中,购买商品的数量上限
	 */
	private int buyLimit;
	
	
	private int pro_id;
	private String productProm;
	private int brand_id;
	private String description;
	private String inventoryArea;
	private int proCount;
	private int salesNum;
	private int score;
	private String shelvesTime;
	private int state;
	
	

	public int getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(int buyLimit) {
		this.buyLimit = buyLimit;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public String getProductProm() {
		return productProm;
	}

	public void setProductProm(String productProm) {
		this.productProm = productProm;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInventoryArea() {
		return inventoryArea;
	}

	public void setInventoryArea(String inventoryArea) {
		this.inventoryArea = inventoryArea;
	}

	public int getProCount() {
		return proCount;
	}

	public void setProCount(int proCount) {
		this.proCount = proCount;
	}

	public int getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getShelvesTime() {
		return shelvesTime;
	}

	public void setShelvesTime(String shelvesTime) {
		this.shelvesTime = shelvesTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getProdAccount() {
		return prodAccount;
	}

	public void setProdAccount(String prodAccount) {
		this.prodAccount = prodAccount;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * 限时特价
	 */
	private String limitprice;
	/**
	 * 剩余时间
	 */
	private String lefttime;
	/**
	 * 商品属性bean
	 */

	public List<ProductProperty> product_property;

	/**
	 * @roseuid 534B81F90306
	 */
	public Product() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLimitprice() {
		return limitprice;
	}

	public void setLimitprice(String limitprice) {
		this.limitprice = limitprice;
	}

	public String getLefttime() {
		return lefttime;
	}

	public void setLefttime(String lefttime) {
		this.lefttime = lefttime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getUplimit() {
		return uplimit;
	}

	public void setUplimit(int uplimit) {
		this.uplimit = uplimit;
	}

	public boolean isIsgift() {
		return isgift;
	}

	public void setIsgift(boolean isgift) {
		this.isgift = isgift;
	}

	public List<ProductProperty> getProduct_property() {
		return product_property;

	}

	public void setProduct_property(List<ProductProperty> product_property) {
		this.product_property = product_property;

	}

}
