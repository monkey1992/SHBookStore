package com.xiaoyu.shbookstore.ui;


import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.customctrl.MyScrollView;
import com.xiaoyu.shbookstore.domain.AddressInfo;
import com.xiaoyu.shbookstore.domain.CheckoutAddup;
import com.xiaoyu.shbookstore.domain.DeliveryInfo;
import com.xiaoyu.shbookstore.domain.InvoiceInfo;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.domain.PaymentInfo;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.engine.OrderEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class OrderDetailView extends BaseView {
	
	private OrderEngine orderEngine;
	private RelativeLayout rlProgres;
	private MyScrollView mvScroll;
	
	private RelativeLayout rlGoodsTrackDetail;//跳转到物流信息
	private TextView btnBack;//返回上一界面
	private TextView tvCancelOrder;//取消订单
	/**
	 * 地址相关
	 */
	private TextView tvAdrReceiver;//收件人
	private TextView tvAdrPhone;//收件人电话
	private TextView tvAdrReAdress;//收件人地址
	private TextView tvAdrPostcode;//收件re邮编
	/**
	 * 订单详情
	 */
	private TextView tvOrderState;//订单状态
	private TextView tvGoodsTrackWay;//送货方式
	private TextView tvPayWay;//支付方式
	private TextView tvOrderTime;//订单生成时间
	private TextView tvPostGoodsTime;//发货时间
	private TextView tvIsInvoiced;//是否开发票
	private TextView tvInvoiceHead;//发票抬头
	private TextView tvPostGoodsRequest;//送货要求
	private TextView tvRemarks;//备注
	/**
	 * 商品信息
	 */
	private ListView lvProductDetail;//商品清单
	private TextView tvAmount;//金额总计
	private TextView tvConcessionalAmount;//优惠金额
	private TextView tvPostAmount;//运费金额
	private TextView tvPayAmount;//已支付金额
	private TextView tvIntegral;//获得积分
	private TextView tvClaimAmount;//应收款金额
	
	private String orderState;
	private String orderid;
	
	/**
	 * 数据封装
	 */
   private List<String> checkout_prom;
   public Order order_info;
   public AddressInfo address_info;
   public CheckoutAddup checkout_addup;
   public InvoiceInfo invoice_info;
   public DeliveryInfo delivery_info;
   public PaymentInfo payment_info;
   private List<Product> productlist;

	public OrderDetailView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		rlGoodsTrackDetail.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		tvCancelOrder.setOnClickListener(this);
	}

	@Override
	public void init() {
		orderEngine = BeanFactory.getImpl(OrderEngine.class);
		System.out.println("我的工厂对象："+orderEngine);
		showInMiddle = (ViewGroup) ViewGroup.inflate(context, R.layout.my_order_detail_activity, null);
		rlProgres = (RelativeLayout) findViewById(R.id.progressBar1);
		mvScroll = (MyScrollView) findViewById(R.id.myscroll);
		rlGoodsTrackDetail = (RelativeLayout) findViewById(R.id.ordr_logistics_rel);
		btnBack = (TextView) findViewById(R.id.head_back_orderlist);
		tvCancelOrder = (TextView) findViewById(R.id.head_cancel_order);
		
		tvAdrReceiver = (TextView) findViewById(R.id.textAdress1);
		tvAdrPhone = (TextView) findViewById(R.id.textAdress2);
		tvAdrReAdress = (TextView) findViewById(R.id.textAdress3);
		tvAdrPostcode = (TextView) findViewById(R.id.textAdress4);
		
		tvOrderState = (TextView) findViewById(R.id.textDetail1);
		tvGoodsTrackWay = (TextView) findViewById(R.id.textDetail2);
		tvPayWay = (TextView) findViewById(R.id.textDetail3);
		tvOrderTime = (TextView) findViewById(R.id.textDetail4);
		tvPostGoodsTime = (TextView) findViewById(R.id.textDetail5);
		tvIsInvoiced = (TextView) findViewById(R.id.textDetail6);
		tvInvoiceHead = (TextView) findViewById(R.id.textDetail7);
		tvPostGoodsRequest = (TextView) findViewById(R.id.textDetail8);
		tvRemarks = (TextView) findViewById(R.id.textDetail9);
		
		lvProductDetail = (ListView) findViewById(R.id.listdetail);
		tvAmount = (TextView) findViewById(R.id.textPrice2);
		tvConcessionalAmount = (TextView) findViewById(R.id.textPrice3);
		tvPostAmount = (TextView) findViewById(R.id.textPrice4);
		tvPayAmount = (TextView) findViewById(R.id.textPrice5);
		tvIntegral = (TextView) findViewById(R.id.textPrice6);
		tvClaimAmount = (TextView) findViewById(R.id.textPrice7);
		System.out.println("bundle的值："+ orderid + orderState);
	}

	@Override
	public void onResume() {
		super.onResume();
		orderid = bundle.getString("orderid");
		orderState = bundle.getString("orderState");
		if (orderState.equals("未处理")) {
			rlGoodsTrackDetail.setVisibility(View.GONE);
			tvCancelOrder.setVisibility(View.VISIBLE);
		}else if (orderState.equals("已付款")) {
			//rlGoodsTrackDetail.setVisibility(View.VISIBLE);
			tvCancelOrder.setVisibility(View.INVISIBLE);
		}else if (orderState.equals("已完成")) {
			rlGoodsTrackDetail.setVisibility(View.GONE);
			tvCancelOrder.setVisibility(View.INVISIBLE);
		}
		inflaterData();
	}
	/**
	 * 填充各个textview的内容
	 */
	private void inflaterData() {
		mvScroll.setVisibility(View.INVISIBLE);
		rlProgres.setVisibility(View.VISIBLE);
		new MyHttpAsyncTask<Void>() {
			String payWay = null;
			String postGoodsTime = null;
			String isInvoice = null;
			String state = null;
			protected void onPostExecute(String result) {
//				if (address_info != null) {
//					tvAdrReceiver.setText(address_info.getName());
//					tvAdrReAdress.setText(address_info.getAddress_area() + address_info.getAddress_detail());
//				}else {
//				}
				tvAdrReceiver.setText("平兄");
				tvAdrReAdress.setText("北京中关村9号楼");
				tvAdrPhone.setText("12345678965");//收件人电话
				tvAdrPostcode.setText("000000");//收件人邮编
				
				
				tvOrderState.setText(state);//订单状态
				tvGoodsTrackWay.setText("红孩子送货");//送货方式，接口文档中没有
				tvPayWay.setText(TextUtils.isEmpty(payment_info.getType())? "免费" : payment_info.getType());//支付方式
				tvOrderTime.setText(order_info.getTime());//订单生成时间
				tvPostGoodsTime.setText(TextUtils.isEmpty(delivery_info.getType())? "周八发货" : delivery_info.getType());//发货时间
				tvIsInvoiced.setText(TextUtils.isEmpty(isInvoice)? "否" : isInvoice);//是否开发票
				tvInvoiceHead.setText(invoice_info.getTitle());//发票抬头
				tvPostGoodsRequest.setText("尽快发货");//送货要求，接口文档中没有
				tvRemarks.setText("注意运送，轻拿轻放");//备注,接口文档中无
				
//				private ListView lvProductDetail;//商品清单----未填充
				
				tvAmount.setText(String.valueOf(checkout_addup.getTotal_price()));//金额总计
				tvConcessionalAmount.setText(String.valueOf(checkout_addup.getProm_cut()));//优惠金额
				tvPostAmount.setText(String.valueOf(checkout_addup.getFreight()));//运费金额
				tvPayAmount.setText("1,000,000,000,000,000");//已支付金额
				tvIntegral.setText(String.valueOf(checkout_addup.getTotal_point()));//获得积分
				tvClaimAmount.setText(
						String.valueOf(checkout_addup.getTotal_price() + checkout_addup.getFreight() - checkout_addup.getProm_cut()));//应收款金额
				mvScroll.setVisibility(View.VISIBLE);
				rlProgres.setVisibility(View.INVISIBLE);
			};
			@SuppressWarnings("unchecked")
			@Override
			protected String doInBackground(Void... params) {
				order_info = (Order) orderEngine.getOrderDetail(orderid).getOrder_info();
				System.out.println("界面开始获取order_info数据");
//				address_info = (AddressInfo) orderEngine.getOrderDetail(orderid, ConstantValue.ADDRESSinfo);
//				System.out.println("界面开始获取address_info数据");
				checkout_addup = (CheckoutAddup) orderEngine.getOrderDetail(orderid).getCheckout_addup();
				System.out.println("界面开始获取checkout_addup数据");
				invoice_info = (InvoiceInfo) orderEngine.getOrderDetail(orderid).getInvoice_info();
				System.out.println("界面开始获取invoice_info数据");
				delivery_info = (DeliveryInfo) orderEngine.getOrderDetail(orderid).getDelivery_info();
				System.out.println("界面开始获取delivery_info数据");
				payment_info = (PaymentInfo) orderEngine.getOrderDetail(orderid).getPayment_info();
				System.out.println("界面开始获取payment_info数据");
				productlist = (List<Product>) orderEngine.getOrderDetail(orderid).getProductlist();
				System.out.println("界面开始获取productlist数据");
				checkout_prom = (List<String>) orderEngine.getOrderDetail(orderid).getCheckout_prom();
				System.out.println("界面开始获取checkout_prom数据");
				//订单状态
				if (order_info.getStatus().equals("1")) {
					state = "已付款";
				}else if (order_info.getStatus().equals("2")) {
					state = "已完成";
				}else if (order_info.getStatus().equals("3")) {
					state = "已取消";
				}
//				//支付类型，1=>货到付款 2=>货到POS机 3=>支付宝(待定)
//				switch (payment_info.getType()) {
//				case 1:
//					payWay = "货到付款";
//					break;
//				case 2:
//					payWay = "货到POS机";
//					break;
//				case 3:
//					payWay = "支付宝";
//					break;
//				}
//	发货时间 //1 => 周一至周五送货 
//				2=> 双休日及公众假期送货 
//				3=> 时间不限，工作日双休日及公众假期均可送货
//				switch (delivery_info.getType()) {
//				case 1:
//					postGoodsTime = "周一至周五送货 ";
//					break;
//				case 2:
//					postGoodsTime = "双休日及公众假期送货 ";
//					break;
//				case 3:
//					postGoodsTime = "时间不限，工作日双休日及公众假期均可送货 ";
//					break;
//				}
				if (invoice_info != null) {
					isInvoice = "是";
				}else {
					isInvoice = "否";
				}
				return null;
			}
		}.executeProxy();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ordr_logistics_rel://物流跟踪
			if (orderState != null && orderState.equals("已付款")) {
				UiManager.getUiManager().changeView(OrderGoodsTrackView.class, bundle);
			}else{
				Toast.makeText(context, "此订单无物流信息", 0).show();
				return;
			}
			break;
		case R.id.head_back_orderlist://返回
			UiManager.getUiManager().changeView(OrderListView.class);
			break;
		case R.id.head_cancel_order://订单取消
			isCancel();
			break;
		default:
			break;
		}
		
	}
	private void isCancel() {
		new MyHttpAsyncTask<Void>() {
			private boolean cancelOrder;
			protected void onPostExecute(String result) {
				if (cancelOrder) {
					Toast.makeText(context, "成功取消", 0).show();
					bundle = new Bundle();
					bundle.putString("cancelId", orderid);
					UiManager.getUiManager().changeView(OrderListView.class, bundle);
				}else {
					Toast.makeText(context, "服务器忙......", 0).show();
				}
			};
			@Override
			protected String doInBackground(Void... params) {
				cancelOrder = orderEngine.cancelOrder(orderid);
				return null;
			}
		}.executeProxy();
	}
	
	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_ORDERDETAIL;
	}
	
	
}
