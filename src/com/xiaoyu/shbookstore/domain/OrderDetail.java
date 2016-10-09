package com.xiaoyu.shbookstore.domain;

import java.util.List;

/**
 * 订单详情
 */
public class OrderDetail 
{
   private String response;
   public String getResponse() {
	return response;
}
   public void setResponse(String response) {
	this.response = response;
}
/**
    * 促销信息
    */
   private List<String> checkout_prom;
   public Order order_info;
   public AddressInfo address_info;
   public CheckoutAddup checkout_addup;
   public InvoiceInfo invoice_info;
   public DeliveryInfo delivery_info;
   public PaymentInfo payment_info;
   
   private List<Product> productlist;
   /**
    * @roseuid 534B80BA02C3
    */
   public OrderDetail() 
   {
    
   }
public List<String> getCheckout_prom() {
	return checkout_prom;
}
public void setCheckout_prom(List<String> checkout_prom) {
	this.checkout_prom = checkout_prom;
}
public Order getOrder_info() {
	return order_info;
}
public void setOrder_info(Order order_info) {
	this.order_info = order_info;
}
public AddressInfo getAddress_info() {
	return address_info;
}
public void setAddress_info(AddressInfo address_info) {
	this.address_info = address_info;
}
public CheckoutAddup getCheckout_addup() {
	return checkout_addup;
}
public void setCheckout_addup(CheckoutAddup checkout_addup) {
	this.checkout_addup = checkout_addup;
}
public InvoiceInfo getInvoice_info() {
	return invoice_info;
}
public void setInvoice_info(InvoiceInfo invoice_info) {
	this.invoice_info = invoice_info;
}
public DeliveryInfo getDelivery_info() {
	return delivery_info;
}
public void setDelivery_info(DeliveryInfo delivery_info) {
	this.delivery_info = delivery_info;
}
public PaymentInfo getPayment_info() {
	return payment_info;
}
public void setPayment_info(PaymentInfo payment_info) {
	this.payment_info = payment_info;
}
public List<Product> getProductlist() {
	return productlist;
}
public void setProductlist(List<Product> productlist) {
	this.productlist = productlist;
}


   
}
