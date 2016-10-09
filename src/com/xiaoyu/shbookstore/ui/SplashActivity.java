package com.xiaoyu.shbookstore.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xiaoyu.shbookstore.R;

public class SplashActivity extends Activity {
	
	// 创建一个消息处理器
	private Handler	handler = new Handler() {
		public void handleMessage(android.os.Message msg) {					
				loadMainUI();						
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		handler.sendEmptyMessageDelayed(0, 2000);
	}

	/*
	 * 进入主界面
	 */
	protected void loadMainUI() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}	
}
