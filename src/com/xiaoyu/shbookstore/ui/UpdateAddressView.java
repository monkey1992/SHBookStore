package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.AddressListInfo;
import com.xiaoyu.shbookstore.engine.AddAddressViewEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class UpdateAddressView extends BaseView {
	
	private EditText add_address_name_edit;//收货人
	private EditText add_address_mobile_edit;//手机
	private EditText add_address_detail_edit;//详细地址
	private Button save_address_button;//保存按钮
	private Button delete_address_button;//删除按钮按钮
	private AddAddressViewEngine engine;
	private UiManager uiManager;
	
	private Map<String,String> params;
	private String areadetail;
	private String name;
	private String phonenumber;
	private List<AddressListInfo> addSuccessToNet;
	
	public UpdateAddressView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		
		//点击保存地址到服务器并返回地址管理界面
				save_address_button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						name =add_address_name_edit.getText().toString();
						phonenumber =add_address_mobile_edit.getText().toString();
						areadetail =add_address_detail_edit.getText().toString();
						
						if(name==null||name.equals("")){
							Toast.makeText(context, "收货人不能为空", 0).show();
							return;
						}else if(phonenumber==null||phonenumber.equals("")){
							Toast.makeText(context, "电话号码不能为空", 0).show();
							return;
						}else if(areadetail==null||areadetail.equals("")){
							Toast.makeText(context, "收货地址不能为空", 0).show();
							return;
						}
						
						params = new HashMap<String, String>();
						params.put("name", name);
						params.put("phonenumber", phonenumber);
						params.put("areadetail", areadetail);
						params.put("id", bundle.get("addressid").toString());
						
						new MyHttpAsyncTask<Map>() {

							@Override
							protected String doInBackground(Map... params) {
								addSuccessToNet = engine.addSuccessToNet(ConstantValue.COMMON_URI.concat(ConstantValue.ADDRESSSAVE), params[0]);
								return "";
							}
							
							@Override
							protected void onPostExecute(String result) {
									Toast.makeText(context,"修改成功", 0).show();
									//添加成功返回地址管理界面
									uiManager.changeView(AddressManagerView.class);
								}
						}.executeProxy(params);
					}
				});
		
				//点击取消返回地址管理界面
				delete_address_button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						uiManager.changeView(AddressManagerView.class);
					}
				});
	}

	@Override
	public void init() {
		showInMiddle =(ViewGroup) View.inflate(context, R.layout.update_address_activity,null);
		add_address_name_edit =(EditText) findViewById(R.id.add_address_name_edit);
		add_address_mobile_edit=(EditText) findViewById(R.id.add_address_mobile_edit);
		add_address_detail_edit =(EditText) findViewById(R.id.add_address_detail_edit);
		save_address_button =(Button) findViewById(R.id.save_address_button);
		delete_address_button =(Button) findViewById(R.id.delete_address_button);
		uiManager = UiManager.getUiManager();
		
		engine = BeanFactory.getImpl(AddAddressViewEngine.class);
	
	}
	
	@Override
	public void onResume() {
		
		add_address_name_edit.setText(bundle.get("name").toString());
		add_address_mobile_edit.setText(bundle.get("phonenumber").toString());
		add_address_detail_edit.setText(bundle.get("areadetail").toString());
		
		super.onResume();
	}
	@Override
	public int getIdentifier() {
		return ConstantValue.UPDATEADDRESSVIEW;
	}

}
