package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.AddressListInfo;
import com.xiaoyu.shbookstore.engine.AddressListViewEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

/**
 * 
 * 地址列表
 * 
 * @author GP
 * 
 */
public class AddressListView extends BaseView {

	public static final String TAG = "AddressListView";

	// 地址列表的listview
	private ListView address_list_list;
	
	//地址列表bean
	private AddressListInfo info;
	UiManager uiManager = UiManager.getUiManager();
	

	// 发送请求到服务器需要带的参数
	Map<String, String> params;
	// 存放解析完json的集合
	List<AddressListInfo> addrListInfo;
	TextView paycenter;
	TextView address_list_add_text;
	

	public AddressListView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {

	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context,R.layout.address_list_activity, null);
		paycenter =(TextView) findViewById(R.id.head_paycenter_text);
		address_list_add_text =(TextView) findViewById(R.id.address_list_add_text);
		
		address_list_list = (ListView) findViewById(R.id.address_list_list);

		params = new HashMap<String, String>();
		params.put("Version", "1.1");
		
		final AddressListViewEngine engin = BeanFactory.getImpl(AddressListViewEngine.class);
		
		/*
		 * 设置返回结算中的按钮点击事件
		 */
		paycenter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				uiManager.changeView(PayCenter.class);//返回结算中心，不带信息bundle
			}
		});
		/*
		 * 设置新增地址按钮点击事件
		 */
		address_list_add_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				uiManager.changeView(AddAddressView.class);//不带bundle信息
			}
		});
		
		/**
		 * 网络异步请求
		 */
		new MyHttpAsyncTask<Map>() {
			@Override
			protected String doInBackground(Map... params) {
				addrListInfo = engin.getAddrListInfoFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.ADDRESSLIST), params[0]);
				
				return "";
			}
			@Override
			protected void onPostExecute(String result) {
				MyAdapter adapter = new MyAdapter();
				if(addrListInfo!=null){
					address_list_list.setAdapter(adapter);
					//设置listView的item点击事件
					address_list_list.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							Bundle addrBundle = new Bundle();
							AddressListInfo info2 = addrListInfo.get(position);
							addrBundle.clear();
							addrBundle.putString("name", info2.getName());
							addrBundle.putString("phonenumber", info2.getPhonenumber());
							addrBundle.putString("areadetail", info2.getAreadetail());
							addrBundle.putString("areaid", info2.getAreaid());
							
							uiManager.changeView(PayCenter.class, addrBundle);
						}
					});
				}
			}
		}.executeProxy(params);
		
	}
	
	/**
	 * 
	 * 给listview 定义适配器
	 * 
	 */
	private class MyAdapter extends BaseAdapter {
		
		@Override
		public int getCount() {
			return addrListInfo.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView != null) {
				viewHolder = (ViewHolder) convertView.getTag();
			} else {
				viewHolder = new ViewHolder();
				convertView = View.inflate(context, R.layout.address_list_items, null);

				viewHolder.addressnameTV = (TextView) convertView.findViewById(R.id.addressnameTV);
				viewHolder.phoneTV = (TextView) convertView.findViewById(R.id.phoneTV);
				viewHolder.addressdetilTV = (TextView) convertView.findViewById(R.id.addressdetilTV);
				convertView.setTag(viewHolder);
			}
			
			info = addrListInfo.get(position);
			
			viewHolder.addressnameTV.setText(info.getName());
			viewHolder.phoneTV.setText(info.getPhonenumber());
			viewHolder.addressdetilTV.setText(info.getAreadetail());
			
			// 给item设置点击事件
//			convertView.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Bundle addrBundle = new Bundle();
//					addrBundle.putString("name", info.getName());
//					addrBundle.putString("phonenumber", info.getPhonenumber());
//					addrBundle.putString("areadetail", info.getAreadetail());
//					uiManager.changeView(PayCenter.class, addrBundle);
//				}
//			});
			return convertView;
		}
		
		class ViewHolder {
			TextView addressnameTV;// 姓名
			TextView phoneTV; // 电话号码
			TextView addressdetilTV; // 详细地址
		}
		
	}
	
	@Override
	public int getIdentifier() {
		return ConstantValue.ADDRESSLISTVIEW;
	}

}
