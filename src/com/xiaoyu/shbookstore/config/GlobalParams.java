package com.xiaoyu.shbookstore.config;

import com.xiaoyu.shbookstore.domain.MyError;


/**
 * 保存全局变量
 */
public class GlobalParams {
	/**
	 * 手机代理IP
	 */
	public static String PROXY;
	/**
	 * 代理端口
	 */
	public static int PORT;
	
	/**
	 * 屏幕宽度
	 */
	public static int windowWidth;
	/**
	 * 是否登录
	 */
	public static boolean isLogin;
	/**
	 * 用户名称
	 */
	public static String USERNAME;
	/**
	 * 用户余额
	 */
	public static Float MONEY;
	
	
	/**
	 * 是否选择支付方式
	 */
	public static boolean ispayvalue;
	/**
	 * 是否选择送货时间
	 */
	public static boolean issendtime;
	
	
	/**
	 * 错误bean的引�?
	 */
	public static MyError myError;
	
	
	/**
	 * 前一个界面的id
	 */
	public static int preViewId;
	
	/**
	 * paymentid
	 */
	public static int paymentid;
	
	/**
	 * deliveryid
	 */
	public static int deliveryid;
	
	/**
	 * invoicetype
	 */
	public static int invoicetype=1;
	
	
	
	/**
	 * 验证是否登录的身份令牌id
	 */
	public static String userId;
	
	
}	
