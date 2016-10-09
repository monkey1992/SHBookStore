package com.xiaoyu.shbookstore.engine.impl;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.domain.InvoiceInfo;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.domain.PayMentBean;
import com.xiaoyu.shbookstore.engine.BaseEngine;
import com.xiaoyu.shbookstore.engine.PaymentEngine;

public class PaymentengineImpl extends BaseEngine implements PaymentEngine {

	
	/*@Override
	public AddressInfo getAddressInfo(String uri, Map<String, String> params,String str) {
		JSONObject jsonObject = getJSONObject(uri, params);
		String string;
		try {
			string = jsonObject.getString(str);
			AddressInfo info=JSON.parseObject(string,AddressInfo.class);
			return info;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaymentInfo getPaymentInfo(String uri, Map<String, String> params) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String addrinfoJson = httpClientUtil.sendPost(uri, params);
		PaymentInfo info;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(addrinfoJson);
			if(checkResponse(jsonObject)){
				String string = jsonObject.getString("payment_info");
				info = JSON.parseObject(string, PaymentInfo.class);
				return info;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DeliveryInfo getDeliveryInfo(String uri, Map<String, String> params) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String addrinfoJson = httpClientUtil.sendPost(uri, params);
		DeliveryInfo info;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(addrinfoJson);
			if(checkResponse(jsonObject)){
				String string = jsonObject.getString("delivery_info");
				info = JSON.parseObject(string, DeliveryInfo.class);
				return info;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getProductList(String uri, Map<String, String> params) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String addrinfoJson = httpClientUtil.sendPost(uri, params);
		ArrayList<Product> info;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(addrinfoJson);
			if(checkResponse(jsonObject)){
				String string = jsonObject.getString("productlist");
				List<Product> list = JSON.parseArray(string, Product.class);
				return list;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductProperty getProductProperty(String uri,
			Map<String, String> params) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String addrinfoJson = httpClientUtil.sendPost(uri, params);
		ProductProperty info;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(addrinfoJson);
			if(checkResponse(jsonObject)){
				String string = jsonObject.getString("product_property");
				info = JSON.parseObject(string, ProductProperty.class);
				return info;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CheckoutAddup getCheckoutAddup(String uri, Map<String, String> params) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String addrinfoJson = httpClientUtil.sendPost(uri, params);
		CheckoutAddup info;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(addrinfoJson);
			if(checkResponse(jsonObject)){
				String string = jsonObject.getString("checkout _addup");
				info = JSON.parseObject(string, CheckoutAddup.class);
				return info;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}*/

	@Override
	public Order getOrder(String uri, Map<String, String> params) {
		String str=getServiceString(uri, params);
		Order order = JSON.parseObject(str, Order.class);
		return order;
	}

	@Override
	public InvoiceInfo getInvoiceInfo(String uri) {
		String str=getServiceString(uri, null);
		InvoiceInfo invoiceInfo = JSON.parseObject(str,InvoiceInfo.class);
		return invoiceInfo;
	}

	@Override
	public PayMentBean getPayMentBean(String uri, Map<String, String> params) {
			String str=getServiceString(uri, params);
			//String str="";
			PayMentBean info=JSON.parseObject(str,PayMentBean.class);
			return info;
	}

	
	

}
