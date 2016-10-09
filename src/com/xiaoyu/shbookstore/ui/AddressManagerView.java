package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.AddressListInfo;
import com.xiaoyu.shbookstore.engine.AddressManagerViewEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

/**
 * 
 * 地址列表
 * 
 * @author GP
 * 
 */
public class AddressManagerView extends BaseView {
	// 地址管理列表的listview
	private ListView address_manage_list;
	 //地址列表bean
	private AddressListInfo info;
	// 解析完的json 数据放在集合里面
	private List<AddressListInfo> addrListInfo;
	// 发送请求到服务器需要带的参数
	private Map<String, String> params;
	
	private TextView moreView ;
	private TextView address_manager_add_text;
	private MyAdapter adapter = new MyAdapter();
	
	private UiManager uiManager =UiManager.getUiManager();
	private AddressManagerViewEngine  engine;
	
	public AddressManagerView(Context context) {
		super(context);
	}
	@Override
	public int getIdentifier() {
		return ConstantValue.ADDRESSMANAGERVIEW;
	}
	@Override
	public void setListener() {
		
	}
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(adapter==null){
				adapter = new MyAdapter();
				getData(engine);
				address_manage_list.setAdapter(adapter);
				
			}else{
				getData(engine);
				address_manage_list.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		}
	};

	@Override
	public void init() {
		showInMiddle= (ViewGroup) View.inflate(context, R.layout.address_manage_activity, null);
		address_manage_list =(ListView) findViewById(R.id.address_manage_list);
		address_manager_add_text =(TextView) findViewById(R.id.address_manager_add_text);
		engine = BeanFactory.getImpl(AddressManagerViewEngine.class);
		params = new HashMap<String, String>();
		params.put("Version", "1.1");
		
		
		moreView =(TextView) showInMiddle.findViewById(R.id.head_back_text);
		
		/*
		 * 设置返回按钮点击事件，返回更多界面
		 */
		moreView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				uiManager.changeView(MoreView.class);//不带信息bundle
			}
		});
		/*
		 * 设置添加按钮点击事件，进入添加地址界面
		 */
		address_manager_add_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				uiManager.changeView(AddAddressView.class);//不带信息bundle
			}
		});
		
		getData(engine);//得到网络数据
	}
	private void getData(final AddressManagerViewEngine engine) {
		new MyHttpAsyncTask<Map>() {
			@Override
			protected String doInBackground(Map... params) {
				addrListInfo = engine.getAddrListInfoFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.ADDRESSLIST), params[0]);
				return "";
			}
			@Override
			protected void onPostExecute(String result) {
				if(addrListInfo!=null){
					address_manage_list.setAdapter(adapter);
				}
			}
		}.executeProxy(params);
	}

	
	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return addrListInfo.size();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null){
				holder = new ViewHolder();
				convertView =View.inflate(context, R.layout.address_manage_listitem, null);
				
				holder.address_listitem_receiver_text = (TextView) convertView.findViewById(R.id.address_listitem_receiver_text);
				holder.address_listitem_phone_text = (TextView) convertView.findViewById(R.id.address_listitem_phone_text);
				holder.address_listitem_ads_text = (TextView) convertView.findViewById(R.id.address_listitem_ads_text);
				holder.address_manage_delete_btn =(TextView) convertView.findViewById(R.id.address_manage_delete_btn);
				holder.address_manage_update_btn =(TextView) convertView.findViewById(R.id.address_manage_update_btn);
				
				convertView.setTag(holder);
			}else{
				holder =(ViewHolder) convertView.getTag();
			}
			info = addrListInfo.get(position);
			holder.address_listitem_receiver_text.setText(info.getName());
			holder.address_listitem_phone_text.setText(info.getPhonenumber());
			holder.address_listitem_ads_text.setText(info.getAreadetail());
			
			
			final Map<String,String> delMap = new HashMap<String, String>();
			delMap.put("id", info.getAreaid());
			
			//设置删除按钮
			holder.address_manage_delete_btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					new MyHttpAsyncTask<Map>() {
						@Override
						protected String doInBackground(Map... params) {
							HttpClientUtil httpClientUtil = new HttpClientUtil();
							String sendGet = httpClientUtil.sendGet(ConstantValue.COMMON_URI.concat(ConstantValue.ADDRESSDELETE), params[0]);
							return sendGet;
						}
						@Override
						protected void onPostExecute(String result) {
							handler.sendEmptyMessage(0);
							if(result.contains("addressdelete")){
								Toast.makeText(context, "删除成功", 0).show();
							}else{
								Toast.makeText(context, "删除失败", 0).show();
							}
							
						}
					}.executeProxy(delMap);
				}
			});
			
			//设置修改按钮点击事件
			holder.address_manage_update_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AddressListInfo info = addrListInfo.get(position);
					Bundle bundle = new Bundle();
					bundle.putString("name", info.getName());
					bundle.putString("phonenumber", info.getPhonenumber());
					bundle.putString("areadetail", info.getAreadetail());
					bundle.putString("addressid", info.getAddress_id());
					uiManager.changeView(UpdateAddressView.class, bundle);
				}
			});
			
				
			return convertView;
		}
		
		class ViewHolder {
			TextView address_listitem_receiver_text;// 姓名
			TextView address_listitem_phone_text; // 电话号码
			TextView address_listitem_ads_text; // 详细地址
			TextView address_manage_delete_btn; // 删除
			TextView address_manage_update_btn; // 修改
			
		}
	}
	
	@Override
	public void onResume() {
		getData(engine);
		super.onResume();
	}
}





























