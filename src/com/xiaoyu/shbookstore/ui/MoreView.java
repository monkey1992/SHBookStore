package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.manager.UiManager;

public class MoreView extends BaseView {	
	private RelativeLayout my_account_rl;
	private RelativeLayout my_order_rl;
	private RelativeLayout address_manage_rl;
	private RelativeLayout my_favorite_rl;
	private RelativeLayout recent_browse_rl;
	private RelativeLayout helpRelLay;
	private RelativeLayout userfeedback;
	private RelativeLayout aboutRelLay;
	
	public MoreView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		my_account_rl.setOnClickListener(this);
		my_order_rl.setOnClickListener(this);
		address_manage_rl.setOnClickListener(this);
		my_favorite_rl.setOnClickListener(this);
		recent_browse_rl.setOnClickListener(this);
		helpRelLay.setOnClickListener(this);
		userfeedback.setOnClickListener(this);
		aboutRelLay.setOnClickListener(this);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.more_activity, null);
		my_account_rl = (RelativeLayout) findViewById(R.id.my_account_rl);
		my_order_rl = (RelativeLayout) findViewById(R.id.my_order_rl);
		address_manage_rl = (RelativeLayout) findViewById(R.id.address_manage_rl);
		my_favorite_rl = (RelativeLayout) findViewById(R.id.my_favorite_rl);
		recent_browse_rl = (RelativeLayout) findViewById(R.id.recent_browse_rl);
		helpRelLay = (RelativeLayout) findViewById(R.id.helpRelLay);
		userfeedback = (RelativeLayout) findViewById(R.id.userfeedback);
		aboutRelLay = (RelativeLayout) findViewById(R.id.aboutRelLay);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_account_rl:
			if(GlobalParams.isLogin) {
				UiManager.getUiManager().changeView(MyAccount.class);
			}else {
				UiManager.getUiManager().changeView(LoginView.class);
			}
			break;
		case R.id.my_order_rl:
			if(GlobalParams.isLogin) {
				UiManager.getUiManager().changeView(OrderListView.class);
			}else {
				UiManager.getUiManager().changeView(LoginView.class);
			}
			break;
		case R.id.address_manage_rl:
			if(GlobalParams.isLogin) {
				UiManager.getUiManager().changeView(AddressManagerView.class);
			}else {
				UiManager.getUiManager().changeView(LoginView.class);
			}
			break;
		case R.id.my_favorite_rl:
			if(GlobalParams.isLogin) {
				UiManager.getUiManager().changeView(FavoriteView.class);
			}else {
				UiManager.getUiManager().changeView(LoginView.class);
			}
			break;
		case R.id.recent_browse_rl:
			
			break;
		case R.id.helpRelLay:
			
			break;
		case R.id.userfeedback:
			
			break;
		case R.id.aboutRelLay:
			
			break;
		default:
			break;
		}
		super.onClick(v);
	}
	
	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_MORE;
	}

}
