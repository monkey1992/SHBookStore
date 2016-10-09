package com.xiaoyu.shbookstore.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.domain.Logistics;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.domain.OrderDetail;
import com.xiaoyu.shbookstore.engine.BaseEngine;
import com.xiaoyu.shbookstore.engine.OrderEngine;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class OrderEngineImpl extends BaseEngine implements OrderEngine {
	
	private JSONObject object;
	private List<Order> list;
	private static Map<String, String> map1 = new HashMap<String, String>();
	private static Map<String, String> map2 = new HashMap<String, String>();
	private static Map<String, String> map3 = new HashMap<String, String>();
	static{
		map1.put("type", "1");
		map1.put("page", "1");
		map1.put("pageNum", "10");
		map2.put("type", "2");
		map2.put("page", "1");
		map2.put("pageNum", "10");
		map3.put("type", "3");
		map3.put("page", "1");
		map3.put("pageNum", "10");
	}
	private OrderDetail orderDetailBean;

	@Override
	public List<Order> getOrderList(String type, String page, String pagenumber) {
		Map<String, String> map = new HashMap<String, String>();
		HttpClientUtil clientUtil = new HttpClientUtil();
		map.put("type", type);
		map.put("page", page);
		map.put("pageNum", pagenumber);
		String orderList = clientUtil.sendGet(ORDERLISTURI, map);
		System.out.println(orderList);
		try {
			 object = new JSONObject(orderList);
			if(checkResponse(object)){
				String orderlist = object.getString("orderlist");
				System.out.println("列表获得的字符串"+orderlist);
				 list = JSON.parseArray(orderlist, Order.class);
				 System.out.println("all：" + list.toString());
//				if (type.equals("1")) {
//					list = getSpecificOrderList(list, "1");
//					System.out.println(type + "：未处理：" + list.toString());
//				}else if (type.equals("3")) {
//					list = getSpecificOrderList(list, "3");
//					System.out.println(type + "：已取消：" + list.toString());
//				}
//				if (list != null) {
//					System.out.println("传递给界面："+ list.toString());
					return list;
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> getSpecificOrderList(List<Order> list, String str) {
		List<Order> resault = new ArrayList<Order>();
		if (list.size() != 0) {
			for (Order order : list) {
				if (order.getStatus().equals(str)) {
					resault.add(order);
				}
			}
		}
		return resault;
	}
	
	@Override
	public Logistics getLogistics(String orderId) {
		Map<String, String> map = new HashMap<String, String>();
		HttpClientUtil clientUtil = new HttpClientUtil();
		map.put("orderId", orderId);
		String logisticsString = clientUtil.sendGet(LOGISTICSURI, map);
		System.out.println(logisticsString+"______服务器给我的物流String_______");
		Logistics logistics = new Logistics();
		try {
			object = new JSONObject(logisticsString);
			if(checkResponse(object)){
				logistics = JSON.parseObject(object.getString("logistics"), Logistics.class);
				System.out.println(logistics+"-------------给界面传送的物流bean");
				return logistics;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderDetail getOrderDetail(String orderId) {
		Map<String, String> map = new HashMap<String, String>();
		HttpClientUtil clientUtil = new HttpClientUtil();
		//System.out.println(orderId);
		map.put("orderId", orderId);
		String orderDetail = clientUtil.sendGet(ORDERDETAILURI, map);
		try {
			object = new JSONObject(orderDetail);
			if(checkResponse(object)){
				orderDetailBean = JSON.parseObject(orderDetail, OrderDetail.class);	
				return orderDetailBean;
//				switch (beanNum) {
//				case ConstantValue.ORDERinfo://订单表
//					System.out.println(orderDetailBean.getOrder_info().getTime());
//					return orderDetailBean.getOrder_info();
//				case ConstantValue.ADDRESSinfo://地址bean
//					System.out.println(orderDetailBean.getAddress_info().getAddress_detail());
//					return orderDetailBean.getAddress_info();
//				case ConstantValue.PAYMENTinfo://支付方式
//					System.out.println(orderDetailBean.getPayment_info().getType());
//					return orderDetailBean.getPayment_info();
//				case ConstantValue.DELIVERYinfo://送货时间
//					System.out.println(orderDetailBean.getDelivery_info().getType());
//					return orderDetailBean.getDelivery_info();
//				case ConstantValue.INVOICEinfo://发票
//					System.out.println(orderDetailBean.getInvoice_info().getContent());
//					return orderDetailBean.getInvoice_info();
//				case ConstantValue.PRODUCTlist://商品列表
//					System.out.println(orderDetailBean.getProductlist().get(0));
//					return orderDetailBean.getProductlist();
//				case ConstantValue.CHECKOUTprom://促销信息
//					System.out.println(orderDetailBean.getCheckout_prom().get(0));
//					return orderDetailBean.getCheckout_prom();
//				case ConstantValue.CHECKOUTaddup://总计
//					System.out.println(orderDetailBean.getCheckout_addup().getTotal_count());
//					return orderDetailBean.getCheckout_addup();
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean cancelOrder(String orderId) {
		Map<String, String> map = new HashMap<String, String>();
		HttpClientUtil clientUtil = new HttpClientUtil();
		map.put("orderId", orderId);
		String cancel = clientUtil.sendPost(ORDERCANCELURI , map);
		System.out.println("订单取消是的返回字符串"+cancel);
		JSONObject object;
		try {
			object = new JSONObject(cancel);
			if(checkResponse(object)){
				System.out.println("是否为ordercancel"+object.getString("response"));
				return object.getString("response").equals("ordercancel");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Order> getAllOrderList() {
		List<Order> list1;List<Order> list2;List<Order> list3;
		JSONObject object1;JSONObject object2;JSONObject object3;
		HttpClientUtil clientUtil = new HttpClientUtil();
		String orderList1 = clientUtil.sendGet(ORDERLISTURI, map1);
		try {
			 object1 = new JSONObject(orderList1);
			 if(checkResponse(object1)){
					String orderlist = object1.getString("orderlist");
					System.out.println(orderlist);
					 list1 = JSON.parseArray(orderlist, Order.class);
					 if (list1.size() != 0) {
						 for (Order order : list1) {
								list.add(order);
							}
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		HttpClientUtil clientUtil2 = new HttpClientUtil();
		String orderList2 = clientUtil2.sendGet(ORDERLISTURI, map2);
		try {
			 object2 = new JSONObject(orderList2);
			 if(checkResponse(object2)){
					String orderlist = object2.getString("orderlist");
					System.out.println(orderlist);
					 list2 = JSON.parseArray(orderlist, Order.class);
					 if (list2.size() != 0) {
						 for (Order order : list2) {
								list.add(order);
							}
					}
				}
	}catch (Exception e) {
		e.printStackTrace();
	}
		HttpClientUtil clientUtil3 = new HttpClientUtil();
		String orderList3 = clientUtil3.sendGet(ORDERLISTURI, map1);
		try {
			 object3 = new JSONObject(orderList3);
			 if(checkResponse(object3)){
					String orderlist = object3.getString("orderlist");
					System.out.println(orderlist);
					 list3 = JSON.parseArray(orderlist, Order.class);
					 if (list3.size() != 0) {
						 for (Order order : list3) {
								list.add(order);
							}
					}
				}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	}
}
