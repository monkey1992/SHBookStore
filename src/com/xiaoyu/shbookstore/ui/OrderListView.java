package com.xiaoyu.shbookstore.ui;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.dao.PayDao;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.engine.OrderEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class OrderListView extends BaseView {

	private TextView btnBackAccountCentre;
	private TextView tvOrderNopay;
	private TextView tvOrderAll;
	private TextView tvOrderCancel;
	private TextView tvNullOrder;
	private ListView lvOrderList;
	private OrderlistAdapter adapter;
	
	/**
	 * 数据解析与存储
	 */
	private List<Order> localList;
	private List<Order> allList;
	private List<Order> list;
	private String type = null;
	List<Order> list1;List<Order> list2;List<Order> list3;
	/**
	 * listview分页查询参数
	 */
	private int page = 0;
	private int pageNumber = 10;
	private PayDao dao;
	/**
	 * 业务引擎
	 */
	private OrderEngine engineImpl; 
	
	public OrderListView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		btnBackAccountCentre.setOnClickListener(this);
		tvOrderAll.setOnClickListener(this);
		tvOrderNopay.setOnClickListener(this);
		tvOrderCancel.setOnClickListener(this);
//		// listview注册一个滚动事件的监听器。实现分页查询数据
//		lvOrderList.setOnScrollListener(new OnScrollListener() {
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				switch (scrollState) {
//				case OnScrollListener.SCROLL_STATE_IDLE:// 空闲状态
//					// 判断当前listview滚动的位置
//					// 获取最后一个可见条目在集合里面的位置。
//					int lastposition = lvOrderList.getLastVisiblePosition();
//
//					// 集合里面有20个item 位置从0开始的 最后一个条目的位置 19
//					if (lastposition == (list.size() - 1)) {
//						System.out.println("列表被移动到了最后一个位置，加载更多的数据。。。");
//						page += pageNumber;
//						fillData(type);
//					}
//					break;
//				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 手指触摸滚动
//					break;
//				case OnScrollListener.SCROLL_STATE_FLING:// 惯性滑行状态
//					break;
//				}
//			}
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem,
//					int visibleItemCount, int totalItemCount) {
//			}
//		});
		//listview 条目点击事件
		lvOrderList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				bundle = new Bundle();
				String status = null;
				bundle.putString("orderid", list.get(position).getOrderid());
				if (list.get(position).getStatus().equals("1") || list.get(position).getStatus().equals("未处理")) {
					status = "未处理";
				}else if (list.get(position).getStatus().equals("2")) {
					status = "已付款";
				}
				bundle.putString("orderState", status);

				UiManager.getUiManager().changeView(OrderDetailView.class, bundle);
				
			}
		});
	}

	@Override
	public void init() {
		//dao=new PayDao(context);
		type = "1";
		engineImpl = BeanFactory.getImpl(OrderEngine.class);
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.my_order_activity, null);
		//模块头部按钮：返回用户中心
		btnBackAccountCentre = (TextView) findViewById(R.id.head_back_accountcentre);
		//3个订单列表标题类型
		tvOrderNopay = (TextView) findViewById(R.id.my_order_nopay);
		tvOrderAll = (TextView) findViewById(R.id.my_order_all);
		tvOrderCancel = (TextView) findViewById(R.id.my_order_cancel);
		//无订单数据显示
		tvNullOrder = (TextView) findViewById(R.id.my_order_null_text);
		//有订单数据显示：订单列表listview
		lvOrderList = (ListView) findViewById(R.id.my_order_list);
//		if (dao.getPaymentlist().size() != 0) {
//			localList = getOrderList( dao.getPaymentlist());
//		}
		System.out.println("type：" + type);
		//fillData(type);
		fillData();
		tvNullOrder.setVisibility(View.VISIBLE);
	}
	

	private void fillData() {
//		if (allList == null) {
//			allList = new ArrayList<Order>();
//		}
			new MyHttpAsyncTask<Void>() {
				@Override
				protected void onPostExecute(String result) {
					super.onPostExecute(result);
//					if (localList != null && localList.size()!= 0) {
//						list.addAll(localList);
//					}
//					System.out.println("type为1的list："+list1);
//					System.out.println("type为2的list："+list2);
//					System.out.println("type为3的list："+list3);
//					if (allList != null) {
//						allList.addAll(list1);
//						allList.addAll(list2);
//						allList.addAll(list3);
//					}
//					System.out.println("界面得到的全部集合"+allList);
//					list = engineImpl.getSpecificOrderList(allList, "1");
//					System.out.println("点击"+type+"得到的：" + list);
					inflateData(list);
				}
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
				}
				@Override
				protected String doInBackground(Void...p) {
					list = engineImpl.getOrderList(type, String.valueOf(page), String.valueOf(pageNumber));
					list2 = engineImpl.getOrderList("2", String.valueOf(page), String.valueOf(pageNumber));
					list3 = engineImpl.getOrderList("3", String.valueOf(page), String.valueOf(pageNumber));
					list1 = engineImpl.getOrderList("1", String.valueOf(page), String.valueOf(pageNumber));
					return null;
				}
			}.executeProxy();
//		}else {
//				allList = engineImpl.getSpecificOrderList(allList, stypE);
//				System.out.println(stypE + "------" + allList);
//				if (localList != null) {
//					allList.addAll(localList);
//				}
//				inflateData(allList);
//		}
		
	}

	protected void inflateData(List<Order> list2) {
		if (list2.size() != 0) {
			tvNullOrder.setVisibility(View.INVISIBLE);
			lvOrderList.setVisibility(View.VISIBLE);
			if (adapter == null) {
				adapter = new OrderlistAdapter();
				lvOrderList.setAdapter(adapter);
			}else{
				adapter.notifyDataSetChanged();
				System.out.println("数据重新填充");
			}
		}else {
			tvNullOrder.setVisibility(View.VISIBLE);
			lvOrderList.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.head_back_accountcentre:
			UiManager.getUiManager().changeView(MyAccount.class);
			break;
		case R.id.my_order_nopay:
			tvOrderNopay.setBackgroundResource(R.drawable.segment_selected_1_bg);
			tvOrderNopay.setTextColor(Color.WHITE);
			tvOrderAll.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tvOrderAll.setTextColor(Color.BLACK);
			tvOrderCancel.setBackgroundResource(R.drawable.segment_normal_3_bg);
			tvOrderCancel.setTextColor(Color.BLACK);
			type = "1";
			fillData();
			break;
		case R.id.my_order_all:
//			list.addAll(allList);
//			if (localList != null && localList.size() != 0) {
//				list.addAll(localList);
//			}
//			inflateData(list);
//			list.removeAll(allList);
//			list.removeAll(localList);
			tvOrderNopay.setBackgroundResource(R.drawable.segment_normal_1_bg);
			tvOrderNopay.setTextColor(Color.BLACK);
			tvOrderAll.setBackgroundResource(R.drawable.segment_selected_2_bg);
			tvOrderAll.setTextColor(Color.WHITE);
			tvOrderCancel.setBackgroundResource(R.drawable.segment_normal_3_bg);
			tvOrderCancel.setTextColor(Color.BLACK);
			type = "2";
			fillData();
			break;
		case R.id.my_order_cancel:
			tvOrderNopay.setBackgroundResource(R.drawable.segment_normal_1_bg);
			tvOrderNopay.setTextColor(Color.BLACK);
			tvOrderAll.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tvOrderAll.setTextColor(Color.BLACK);
			tvOrderCancel.setBackgroundResource(R.drawable.segment_selected_3_bg);
			tvOrderCancel.setTextColor(Color.WHITE);
			type = "3";
			fillData();
			break;
		default:
			break;
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		if (bundle != null) {
			String cancelId = bundle.getString("cancelId");
			System.out.println("取消订单后给我的bundle："+cancelId);
			if (cancelId != null) {
				fillData();
			}
		}
		
	}

	private class OrderlistAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();

				convertView = View.inflate(context, R.layout.my_order_listitem, null);

				holder.orderNumber = (TextView) convertView.findViewById(R.id.tv_bianhao_value);
				holder.orderAmount = (TextView) convertView.findViewById(R.id.tv_zonge_value);
				holder.orderState = (TextView) convertView.findViewById(R.id.tv_state_value);
				// needUpdate.add(holder.summary);
				holder.orderTime = (TextView) convertView.findViewById(R.id.tv_ymdhms);

				// A tag can be used to mark a view in its hierarchy and does not have to be unique within the hierarchy.
				// Tags can also be used to store data within a view without resorting to another data structure.
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			/**
			 * 设置条目的数据
			 */
			holder.orderNumber.setText(list.get(position).getOrderid());
			holder.orderAmount.setText(list.get(position).getPrice());
			holder.orderState.setText(list.get(position).getStatus());
			holder.orderTime.setText(list.get(position).getTime());
			
			return convertView;
		}
		// 依据item的layout
		class ViewHolder {
			TextView orderNumber;
			TextView orderAmount;
			TextView orderState;
			TextView orderTime;
		}
	}
	
	
	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_ORDERLIST;
	}

}
