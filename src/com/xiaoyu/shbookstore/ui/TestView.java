package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoyu.shbookstore.R;

public class TestView extends BaseView {


	public TestView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {

	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.home_activity, null);
		
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		
	}

	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}

}
