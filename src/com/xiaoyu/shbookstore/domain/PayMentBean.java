package com.xiaoyu.shbookstore.domain;

import java.util.List;

public class PayMentBean {

	private AddressInfo addr;
	private PaymentInfo payinfo;
	private DeliveryInfo delinfo;
	private List<Product> prolist;
	private CheckoutAddup cheaup;
	public AddressInfo getAddr() {
		return addr;
	}
	public void setAddr(AddressInfo addr) {
		this.addr = addr;
	}
	public PaymentInfo getPayinfo() {
		return payinfo;
	}
	public void setPayinfo(PaymentInfo payinfo) {
		this.payinfo = payinfo;
	}
	public DeliveryInfo getDelinfo() {
		return delinfo;
	}
	public void setDelinfo(DeliveryInfo delinfo) {
		this.delinfo = delinfo;
	}
	public List<Product> getProlist() {
		return prolist;
	}
	public void setProlist(List<Product> prolist) {
		this.prolist = prolist;
	}
	public CheckoutAddup getCheaup() {
		return cheaup;
	}
	public void setCheaup(CheckoutAddup cheaup) {
		this.cheaup = cheaup;
	}
	
}
