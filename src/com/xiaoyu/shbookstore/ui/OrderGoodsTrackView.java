package com.xiaoyu.shbookstore.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.Logistics;
import com.xiaoyu.shbookstore.engine.OrderEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class OrderGoodsTrackView extends BaseView {

	private TextView tvWay;//发货方式
	private TextView tvNumber;//物流编号
	private TextView tvCompany;//快递公司
	private TextView tvBillNumber;//运单号码
	private ListView lvDetail;//物流跟踪的listview
	private TextView tvBackOrderDetail;
	//存储物流追踪信息的list
	private List<String> list;
	private Logistics logistics;
	private LogisticAdapter logisticAdapter;
	private String orderId;
	private RelativeLayout rlProgres;
	private TextView tvNoLogisticsInfo;
	
	public OrderGoodsTrackView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		tvBackOrderDetail.setOnClickListener(this);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.my_order_goods_track_activity, null);
		tvBackOrderDetail = (TextView) findViewById(R.id.head_back_orderDetail);
		
		rlProgres = (RelativeLayout) findViewById(R.id.progressBarforLogistic);
		tvNoLogisticsInfo = (TextView) findViewById(R.id.noLogisticsInfo);
		tvWay = (TextView) findViewById(R.id.textGoodsTrackWay1);
		tvNumber = (TextView) findViewById(R.id.textGoodsTrackWay2);//物流标号接口列表上没有给出
		tvCompany = (TextView) findViewById(R.id.textGoodsTrackWay3);
		tvBillNumber = (TextView) findViewById(R.id.textGoodsTrackWay4);
		lvDetail = (ListView) findViewById(R.id.listGoodsTrackDetail);
	}

	/**
	 * 填充数据
	 */
	private void fillData(final String str) {
		new MyHttpAsyncTask<Void>() {
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if (logistics != null) {
					tvWay.setText(logistics.getExpressway());
					tvCompany.setText(logistics.getLogisticscorp());
					tvBillNumber.setText(logistics.getLogisticsid());
					list = logistics.getList();
					logisticAdapter = new LogisticAdapter();
					lvDetail.setAdapter(logisticAdapter);
					tvNoLogisticsInfo.setVisibility(View.INVISIBLE);
				}else {//假数据
					tvWay.setText("快递");
					tvCompany.setText("火星快递");
					tvBillNumber.setText("123987652341");
					tvNumber.setText("QP3p20100429");
				}
				rlProgres.setVisibility(View.INVISIBLE);
			}
			@Override
			protected String doInBackground(Void... params) {
				logistics = BeanFactory.getImpl(OrderEngine.class).getLogistics(str);
				System.out.println("jiem界面获得的物流信息：------"+logistics);
				return null;
			}
		}.executeProxy();
	}
	@Override
	public void onResume() {
		super.onResume();
		orderId = bundle.getString("orderid");
		System.out.println("---物流请求的参数----"+orderId);
		fillData(orderId);
	}
	private class LogisticAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();

				convertView = View.inflate(context, R.layout.my_order_goods_track_item, null);

				holder.tvLogistics = (TextView) convertView.findViewById(R.id.logisticTrack);

				// A tag can be used to mark a view in its hierarchy and does not have to be unique within the hierarchy.
				// Tags can also be used to store data within a view without resorting to another data structure.
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			/**
			 * 设置条目的数据
			 */
			System.out.println(list.get(position));
			holder.tvLogistics.setText(list.get(position).toString());
			return convertView;
		}
		// 依据item的layout
		class ViewHolder {
			TextView tvLogistics;
		}
		
	}
	@Override
	public void onClick(View v) {
		UiManager.getUiManager().goBack();
	}
	
	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_LOGISTICS;
	}

}
