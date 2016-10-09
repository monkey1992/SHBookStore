package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.UserInfo;
import com.xiaoyu.shbookstore.engine.AccountEngine;
import com.xiaoyu.shbookstore.manager.PromptManager;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class MyAccount extends BaseView{
	
	private TextView tvUserName;
	private TextView tvBound;
	private TextView tvLevel;
	 
	private RelativeLayout llMyOrder;
	private RelativeLayout llMyAddress;
	private RelativeLayout llMyFavorities;
	
	private TextView tvGoback;
	
	private TextView tvLogout;
	private AccountEngine ae;
	private UserInfo userInfo;
	
	public MyAccount(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		llMyOrder.setOnClickListener(this);
		llMyAddress.setOnClickListener(this);
		llMyFavorities.setOnClickListener(this);
		
		tvGoback.setOnClickListener(this);
		tvLogout.setOnClickListener(this);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.my_account_activity, null);
		tvUserName = (TextView) findViewById(R.id.my_name_text);
		tvBound = (TextView) findViewById(R.id.my_bonus_text);
		tvLevel = (TextView) findViewById(R.id.my_level_text);
		
		tvGoback = (TextView) findViewById(R.id.head_back_text);
		tvLogout = (TextView) findViewById(R.id.loginOut_text);
		llMyOrder = (RelativeLayout) findViewById(R.id.ll_my_order);
		llMyAddress = (RelativeLayout) findViewById(R.id.ll_my_address);
		llMyFavorities = (RelativeLayout) findViewById(R.id.ll_my_favorities);
		
		ae = BeanFactory.getImpl(AccountEngine.class);
	}

	@Override
	public void onResume() {
		showUserInfo();
		super.onResume();
	}

	
	
	private void showUserInfo() {
		new MyHttpAsyncTask<Void>() {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if(userInfo != null) {
					tvBound.setText(String.valueOf(userInfo.getBonus()));
					tvLevel.setText(userInfo.getLevel());
				}else {
					PromptManager.showToast(context, GlobalParams.myError.getText());
				}
			}
			@Override
			protected String doInBackground(Void... params) {
				String result = null;
				userInfo = ae.getUserInfo();
				if(userInfo != null) {
					result = "拿到账户信息";
				}
				return result;
			}
		}.executeProxy();
	}
	
	
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ll_my_order:
			UiManager.getUiManager().changeView(OrderListView.class);
			break;
		case R.id.ll_my_address:
			UiManager.getUiManager().changeView(AddressListView.class);
			break;
		case R.id.ll_my_favorities:
			UiManager.getUiManager().changeView(FavoriteView.class);
			break;
		case R.id.head_back_text:
			UiManager.getUiManager().goBack();
			break;
		case R.id.loginOut_text:
			HttpClientUtil client = new HttpClientUtil();
			client.sendPost(ConstantValue.COMMON_URI.concat(ConstantValue.LOGOUT), null);
			break;
		default:
			break;
		}
	}

	
	public int getIdentifier() {
		return ConstantValue.VIEW_ACCOUNT;
	}


}
