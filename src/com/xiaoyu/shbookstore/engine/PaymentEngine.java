package com.xiaoyu.shbookstore.engine;

import java.util.Map;

import com.xiaoyu.shbookstore.domain.InvoiceInfo;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.domain.PayMentBean;

public interface PaymentEngine  {
	

	PayMentBean getPayMentBean(String uri,Map<String,String>params);
	
	Order getOrder(String uri,Map<String,String> params);
	
	InvoiceInfo getInvoiceInfo(String uri);
	/*AddressInfo getAddressInfo(String uri,Map<String,String> params,String str);
	PaymentInfo getPaymentInfo(String uri,Map<String,String> params);
	DeliveryInfo getDeliveryInfo(String uri,Map<String,String> params);
	List<Product> getProductList(String uri,Map<String,String> params);
	ProductProperty getProductProperty(String uri,Map<String,String> params);
	CheckoutAddup getCheckoutAddup(String uri,Map<String,String> params);*/
	
}
