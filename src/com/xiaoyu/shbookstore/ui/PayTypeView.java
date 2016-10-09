package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.manager.UiManager;

public class PayTypeView extends BaseView {

	private RelativeLayout pay_money_rel;
	private RelativeLayout pay_pos_rel;
	private RelativeLayout pay_alipay_rel;
	private ImageView pay_money_img;
	private ImageView pay_pos_img;
	private ImageView pay_alipay_img;
	
	private TextView goback;
	private SharedPreferences sp;
	public PayTypeView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		pay_money_rel.setOnClickListener(this);
		pay_pos_rel.setOnClickListener(this);
		pay_alipay_rel.setOnClickListener(this);
		goback.setOnClickListener(this);
		
	}

	@Override
	public void init() {
		showInMiddle=(ViewGroup) View.inflate(context, R.layout.paytype_activity,null);
		
		pay_money_rel=(RelativeLayout) findViewById(R.id.pay_money_rel);
		pay_pos_rel=(RelativeLayout) findViewById(R.id.pay_pos_rel);
		pay_alipay_rel=(RelativeLayout) findViewById(R.id.pay_alipay_rel);
		
		pay_alipay_img=(ImageView) findViewById(R.id.pay_alipay_img);
		pay_pos_img=(ImageView) findViewById(R.id.pay_pos_img);
		pay_money_img=(ImageView) findViewById(R.id.pay_money_img);
		
		sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		goback=(TextView) findViewById(R.id.head_back_text);
	}
	private boolean flag=true;
	private boolean flag1=true;
	private boolean flag2=true;
	@Override
	public void onClick(View v) {
		Editor edit = sp.edit();
		switch (v.getId()) {
		case R.id.pay_money_rel:
			 //boolean flag=true; 
			if(flag){
				pay_money_img.setVisibility(View.VISIBLE);
				pay_pos_img.setVisibility(View.INVISIBLE);
				pay_alipay_img.setVisibility(View.INVISIBLE);
				edit.putString("paytype", "货到付款——现金支付");
				GlobalParams.paymentid=1;
				flag=false;
				flag1=true;
				flag2=true;
			}else {
				pay_money_img.setVisibility(View.INVISIBLE);
				edit.putString("paytype", null);
				flag=true;
			}
			break;
		case R.id.pay_pos_rel:
			//boolean flag1=true; 
			if(flag1){
				pay_pos_img.setVisibility(View.VISIBLE);
				pay_alipay_img.setVisibility(View.INVISIBLE);
				pay_money_img.setVisibility(View.INVISIBLE);
				edit.putString("paytype", "货到付款——pos支付");
				GlobalParams.paymentid=2;
				flag1=false;
				flag=true;
				flag2=true;
			}else {
				pay_pos_img.setVisibility(View.INVISIBLE);
				edit.putString("paytype", null);
				flag1=true;
			}
			break;
		case R.id.pay_alipay_rel:
			//boolean flag2=true; 
			if(flag2){
				pay_alipay_img.setVisibility(View.VISIBLE);
				pay_money_img.setVisibility(View.INVISIBLE);
				pay_pos_img.setVisibility(View.INVISIBLE);
				edit.putString("paytype", "支付宝——待定");
				GlobalParams.paymentid=3;
				flag2=false;
				flag=true;
				flag1=true;
			}else {
				pay_alipay_img.setVisibility(View.INVISIBLE);
				edit.putString("paytype", null);
				flag2=true;
			}
			break;
		case R.id.head_back_text:
			UiManager.getUiManager().changeView(PayCenter.class);
			break;
		}
		edit.commit();
		super.onClick(v);
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.PAYTYPE;
	}
}
