package com.xiaoyu.shbookstore.domain;

/**
 * 订单信息的封装
 */
public class Order {

	/**
	 * 订单ID
	 */
	private String orderid;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * 订单显示状态
	 */
	private String status;

	/**
	 * 下单时间
	 */
	private String time;

	/**
	 * 订单金额
	 */
	private String price;
	/**
	 * 支付方式
	 */
	private String paymenttype;

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	/**
	 * 订单标识，
	 * 
	 * 1=>可删除可修改 2=>不可修改 3=>已完成
	 */
	private String flag;

	/**
	 * @roseuid 534B81E70297
	 */
	public Order() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
