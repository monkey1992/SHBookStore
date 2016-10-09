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

public class SentTimeView extends BaseView {

	private TextView head_back_text;
	private RelativeLayout send_allday_rel;
	private RelativeLayout send_holiday_rel;
	private RelativeLayout send_workday_rel;
	private ImageView send_allday_img;
	private ImageView send_holiday_img;
	private ImageView send_workday_img;
	private SharedPreferences sp;

	public SentTimeView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		head_back_text.setOnClickListener(this);
		send_workday_rel.setOnClickListener(this);
		send_allday_rel.setOnClickListener(this);
		send_holiday_rel.setOnClickListener(this);
	}

	@Override
	public void init() {
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.sendtime_activity, null);
		head_back_text = (TextView) findViewById(R.id.head_back_text);
		send_workday_rel = (RelativeLayout) findViewById(R.id.send_workday_rel);
		send_allday_rel = (RelativeLayout) findViewById(R.id.send_allday_rel);
		send_holiday_rel = (RelativeLayout) findViewById(R.id.send_holiday_rel);
		send_allday_img = (ImageView) findViewById(R.id.send_allday_img);
		send_holiday_img = (ImageView) findViewById(R.id.send_holiday_img);
		send_workday_img = (ImageView) findViewById(R.id.send_workday_img);
	}

	private boolean flag = true;
	private boolean flag1 = true;
	private boolean flag2 = true;

	@Override
	public void onClick(View v) {
		Editor edit = sp.edit();
		switch (v.getId()) {
		case R.id.head_back_text:
			UiManager.getUiManager().changeView(PayCenter.class);
			break;
		case R.id.send_workday_rel:
			if (flag) {
				send_workday_img.setVisibility(View.VISIBLE);
				send_holiday_img.setVisibility(View.INVISIBLE);
				send_allday_img.setVisibility(View.INVISIBLE);
				edit.putString("sendtime", "只工作日送货");
				GlobalParams.deliveryid=1;
				flag = false;
				flag1 = true;
				flag2 = true;
			} else {
				send_workday_img.setVisibility(View.INVISIBLE);
				edit.putString("sendtime", null);
				flag = true;
			}
			break;
		case R.id.send_allday_rel:
			if (flag1) {
				send_workday_img.setVisibility(View.INVISIBLE);
				send_holiday_img.setVisibility(View.INVISIBLE);
				send_allday_img.setVisibility(View.VISIBLE);
				edit.putString("sendtime", "节假日，工作日均可");
				GlobalParams.deliveryid=3;
				flag1 = false;
				flag = true;
				flag2 = true;
			} else {
				send_allday_img.setVisibility(View.INVISIBLE);
				edit.putString("sendtime", null);
				flag1 = true;
			}
			break;
		case R.id.send_holiday_rel:
			if (flag2) {
				send_workday_img.setVisibility(View.INVISIBLE);
				send_holiday_img.setVisibility(View.VISIBLE);
				send_allday_img.setVisibility(View.INVISIBLE);
				edit.putString("sendtime", "双休日，假日送货");
				GlobalParams.deliveryid=2;
				flag2 = false;
				flag = true;
				flag1 = true;
			} else {
				send_holiday_img.setVisibility(View.INVISIBLE);
				edit.putString("sendtime", null);
				flag2 = true;
			}
			break;
		}
		edit.commit();
		super.onClick(v);
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.SENTIME;
	}
}
