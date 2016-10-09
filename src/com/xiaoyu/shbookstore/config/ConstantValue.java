package com.xiaoyu.shbookstore.config;

public interface ConstantValue {	
	/**
	 * 创建xml文件的格式
	 */
	String ENCODING = "UTF-8";	
	/**
	 * 和服务器连接成功
	 */
	int SUCCESS = 0;
	
/********************************界面标识*****************************************/
	
	/**
	 * 大厅,首页
	 */

	int VIEW_HOME = 601;

	
	/**
	 * 购物车界面
	 */
	int VIEW_SHOPPING_CART = 901;
	
	
	/**
	 * 收藏夹
	 */
	int VIEW_FAVORITE = 401;
	
	/**
	 * 账户中心
	 */
	int VIEW_ACCOUNT = 402;
	
	/**
	 * 登录界面
	 */
	int VIEW_LOGIN = 403;
	
	/**
	 * 商品详情
	 */
	int VIEW_PRODUCT_DETAIL = 410;
	
	/**
	 * 结算中心
	 */
	int PAYCENTER=101;
	/**
	 * 发票界面
	 */
	int INVOICE=102;
	/**
	 * 支付方式
	 */
	int PAYTYPE=103;
	/**
	 * 送货时间
	 */
	int SENTIME=104;
	
/**
 * 留言条
 */
	int REMARK=105;
	/**
	 * 注册界面
	 */
	int VIEW_REGISTER = 404;
	
	/**
	 * 更多界面
	 */
	int VIEW_MORE = 405;

	/**
	 * 促销快报
	 */
	int TOPICVIEW = 301;
	/**
	 * 专题商品列表
	 */
	int TOPICPRODUCTLISTVIEW = 302;
	/**
	 * 推荐品牌
	 */
	int RECOMMENDBRANDVIEW = 303;
	/**
	 * 品牌商品列表
	 */
	int BRANDPRODUCTLISTVIEW = 304;
	/**
	 * 限时抢购
	 */
	int LIMITBUYVIEW = 305;
	/**
	 * 新品上架
	 */
	int NEWPRODUCTVIEW = 306;
	/**
	 * 热门单品
	 */
	int HOTPRODUCTVIEW = 307;

	/**
	 * 订单列表
	 */
	int VIEW_ORDERLIST = 201;
	/**
	 * 订单详情
	 */
	int VIEW_ORDERDETAIL = 202;
	/**
	 * 物流查询
	 */
	int VIEW_LOGISTICS = 203;

	/**
	 * 地址列表id
	 */
	int ADDRESSLISTVIEW =801;
	
	/**
	 * 地址管理view  id
	 */
	int ADDRESSMANAGERVIEW=802;
	
	/**
	 * 添加地址界面
	 */
	int ADDADDRESSVIEW =803;
	
	/**
	 * 更新界面
	 */
	int UPDATEADDRESSVIEW =804;

	/**
	 * 一级分类
	 */
	int VIEW_CATEGORY_FIRST = 701;
	
	/**
	 * 二级分类
	 */
	int VIEW_CATEGORY_SECOND = 702;
/********************************网络地址*****************************************/
	/**
	 * 发票
	 */
	String INVO="/invoice";
	/**
	 * 提交结算
	 */
	String OSUB="/ordersumbit";
	/**
	 * 结算中心信息
	 */
	String CHECK = "/checkout"; 
	/**
	 * 服务器地址
	 */

	//String COMMON_URI = "http://27.38.35.228/ECService";
	String COMMON_URI = "http://10.0.2.2:8080/ECService";

	/**
	 * 帮助连接
	 */
	String HELP = "/help";
	
	/**
	 * 主页连接
	 */
	String HOME = "/home";
	
	/**
	 * 登录连接
	 */
	String LOGIN = "/login";
	
	/**
	 * 促销快报连接
	 */
	String TOPIC ="/topic";
	
	/**
	 * 专题商品列表连接
	 */
	String TOPICPRODUCTLIST = "/topic/plist";
	
	/**
	 * 推荐品牌连接
	 */
	String BRAND = "/brand";
	
	/**
	 * 品牌商品列表连接
	 */
	String BRANDPRODUCTLIST = "/brand/plist";
	
	/**
	 * 限时抢购连接
	 */
	String LIMITBUY = "/limitbuy";
	
	/**
	 * 新品上架连接
	 */
	String NEWPRODUCT = "/newproduct";
	
	/**
	 * 热门单品
	 */
	String HOTPRODUCT = "/hotproduct";

	
	/**
	 * 拿到收藏夹
	 */
	String FAVORITE = "/favorites";
	

	/**
	 * 注册
	 */
	String REGISTER = "/register";
	

	/**
	 * 分类请求
	 */
	String CATEGORY = "http://10.0.2.2:8080/ECService/category";
	
	/**
	 * 地址列表
	 */
	String ADDRESSLIST ="/addresslist";
	
	/**
	 * 订单列表
	 */
	String ORDERLIST = "/orderlist";
	/**
	 * 订单详情
	 */
	String ORDERDETAIL = "/orderdetail";
	/**
	 * 取消订单
	 */
	String ORDERCANCEL= "/ordercancel";
	/**
	 * 物流查询
	 */
	String lOGISTICS = "/logistics";
	/**
	 * 购物车
	 */
	String CART = "/cart";


	
	/**
	 * 注销登录
	 */
	String LOGOUT = "/logout";
	
	/**
	 * 账户信息
	 */
	String USERINFO = "/userinfo";
	

	
	/**
	 * 商品详情
	 */
	String PRODUCTINFO = "/product";
	
	/**
	 * 加入收藏夹
	 */
	String PRODUCT_FAVORITES = "/product/favorites";
	
	/**
	 * 添加地址
	 */
	String ADDRESSSAVE="/addresssave";
	
	/**
	 * 删除地址
	 */
	String ADDRESSDELETE ="/addressdelete";

	/*******************************orderDetail属性标识*****************************************/
	/**
	 * public Order order_info;1
	 */
	int ORDERinfo = 1;
	/**
	 * public AddressInfo address_info;2
	 */
	int ADDRESSinfo = 2;
	/**
	 * public PaymentInfo payment_info;3
	 */
	int PAYMENTinfo = 3;
	/**
	 * public DeliveryInfo delivery_info;4
	 */
	int DELIVERYinfo = 4;
	/**
	 * public InvoiceInfo invoice_info;5
	 */
	int INVOICEinfo = 5;
	/**
	 * private  List<Product>productlist;6
	 */
	int PRODUCTlist = 6;
	/**
	 * private List<String> checkout_prom;7
	 */
	int CHECKOUTprom = 7;
	/**
	 * public CheckoutAddup checkout_addup;8
	 */
	int CHECKOUTaddup = 8;
}
