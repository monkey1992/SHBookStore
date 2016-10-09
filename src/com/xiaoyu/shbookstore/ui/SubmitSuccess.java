package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.manager.UiManager;

public class SubmitSuccess extends BaseView {
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView continue_shoping_text;
	private TextView to_order_detail_text;
	private TextView textShopCarNum;
	
	
	public SubmitSuccess(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		continue_shoping_text.setOnClickListener(this);
		to_order_detail_text.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.continue_shoping_text:
			UiManager.getUiManager().changeView(ShoppingCart.class);
			break;
		case R.id.to_order_detail_text:
			UiManager.getUiManager().changeView(OrderListView.class);
			break;
		}
		super.onClick(v);
	}
@Override
public void onResume() {
	if(bundle!=null){
		tv1.setText(bundle.getString("orderid"));
		tv2.setText(bundle.getString("price"));
		tv3.setText(bundle.getString("paymenttype"));
		textShopCarNum.setText(String.valueOf(bundle.getInt("count")));
	}
	super.onResume();
}
	@Override
	public void init() {
		showInMiddle=(ViewGroup) View.inflate(context, R.layout.order_submit_ok_activity, null);
		tv1=(TextView) findViewById(R.id.orderid_value_text);
		tv2=(TextView) findViewById(R.id.paymoney_value_text);
		tv3=(TextView) findViewById(R.id.paytype_value_text);
		continue_shoping_text=(TextView) findViewById(R.id.continue_shoping_text);
		to_order_detail_text=(TextView) findViewById(R.id.to_order_detail_text);
		textShopCarNum=(TextView) findViewById(R.id.textShopCarNum);
	}

	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}

}
