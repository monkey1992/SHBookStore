package com.xiaoyu.shbookstore.engine;

import java.util.List;

import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.Logistics;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.domain.OrderDetail;

public interface OrderEngine {
	
	/**
	 * 订单列表uri
	 */
	String ORDERLISTURI = ConstantValue.COMMON_URI + ConstantValue.ORDERLIST;
	/**
	 * 订单详情uri
	 */
	String ORDERDETAILURI = ConstantValue.COMMON_URI + ConstantValue.ORDERDETAIL;
	/**
	 * 订单取消uri
	 */
	String ORDERCANCELURI = ConstantValue.COMMON_URI + ConstantValue.ORDERCANCEL;
	/**
	 * 物流查询uri
	 */
	String LOGISTICSURI = ConstantValue.COMMON_URI + ConstantValue.lOGISTICS;
	
	/**
	 * 获得order列表
	 * @param type 列表的类型	1=>未支付订单 2=>所有订单 3=>已取消订单
	 * @param page	从第几页开始查询数据库数据
	 * @param pagenumber	每页查询的个数
	 * @return 存储着Order类的List
	 */
	List<Order> getOrderList(String type, String page, String pagenumber);
	List<Order> getAllOrderList();
	
	/**
	 * 获取特定分类的订单列表
	 * @param list	所有订单
	 * @param str	分类依据【订单状态】
	 * @return	分好类的订单列表
	 */
	List<Order> getSpecificOrderList(List<Order> list, String str);
	
	/**
	 * * 获得订单详情的Bean
	 * @param orderId	向服务器传输的订单id
	 * @param beanNum	该Bean中的属性Bean或者集合对象对应的int值
	 * @return	返回该name对应的属性对象
	 */
	OrderDetail getOrderDetail (String orderId);
	
	/**
	 * 获得物流详情
	 * @param orderId	订单id
	 * @return	返回物流详情的bean
	 */
	Logistics getLogistics(String orderId);
	
	/**
	 * 取消该订单
	 * @param orderId	订单id
	 * @return	返回是否取消成功
	 */
	boolean cancelOrder(String orderId);
}
