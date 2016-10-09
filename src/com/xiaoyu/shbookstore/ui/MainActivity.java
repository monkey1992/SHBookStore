package com.xiaoyu.shbookstore.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class MainActivity extends Activity {
	private RelativeLayout middleContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initView();		
	}
	
	private void initView() {
		middleContainer = (RelativeLayout) findViewById(R.id.ii_middle_container);
		UiManager uiManager = UiManager.getUiManager();
		uiManager.initMiddleContainer(middleContainer, this);
		uiManager.changeView(HomeView.class);
	}
	
	@Override
	public void onBackPressed() {
		boolean result = UiManager.getUiManager().changeOperations();
		if (!result) {
			exitSystem1();
		}
	}
	
	/**
	 * 退出系统
	 */
	private void exitSystem1() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_exit)
		.setTitle(R.string.app_name)
		.setMessage("是否退出应用？").setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					@Override
					public void run() {
						GlobalParams.isLogin = false;
						HttpClientUtil client = new HttpClientUtil();
						client.sendPost(ConstantValue.COMMON_URI.concat(ConstantValue.LOGOUT), null);
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				}).start();
			}
		})
		.setNegativeButton("取消", null)	.show();
	}
	
	/**
	 * �������η��ؼ��˳�
	 */
	/**private void exitSystem() {
		long[] clickTimes = new long[2];
		System.arraycopy(clickTimes, 1, clickTimes, 0, clickTimes.length-1);
		clickTimes[clickTimes.length-1] = SystemClock.uptimeMillis();
		if((SystemClock.uptimeMillis()-500) <= clickTimes[0]) {
			new Thread(new Runnable(){
				@Override
				public void run() {
					GlobalParams.isLogin = false;
					HttpClientUtil client = new HttpClientUtil();
					client.sendPost(ConstantValue.COMMON_URI.concat(ConstantValue.LOGOUT), null);
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			}).start();
		}
	}*/
}
