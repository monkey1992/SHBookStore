package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.manager.PromptManager;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.NetUtil;

public abstract class BaseView implements View.OnClickListener{
	protected Context context;
	/**
	 * 当前界面的容器
	 */
	protected ViewGroup showInMiddle;
	/**
	 * 界面里存储数据的对象
	 */
	protected Bundle bundle;	
	private ImageView home;
	private ImageView search;
	private ImageView classify;
	private ImageView shopping;
	private ImageView more; 
	private TextView carNum;
	
	public BaseView(Context context) {
		this.context = context;
		init();
		setListener();
		initBottom();
	}
	
	private void initBottom() {
		home = (ImageView) findViewById(R.id.imgHome);
		search = (ImageView) findViewById(R.id.imgSearch);
		classify = (ImageView) findViewById(R.id.imgClassify);
		shopping = (ImageView) findViewById(R.id.imgShoppingCar);
		more = (ImageView) findViewById(R.id.imgMore);
		carNum = (TextView) findViewById(R.id.textShopCarNum);	
		home.setOnClickListener(this);
		search.setOnClickListener(this);
		classify.setOnClickListener(this);
		shopping.setOnClickListener(this);
		more.setOnClickListener(this);
	}
	
	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
	
	/**
	 * 给界面里面的控件设置监听
	 */
	public abstract void setListener();
	
	/**
	 * 界面的初始化 
	 */
	public abstract void init();
	
	/**
	 * 当显示界面显示时,会做的操作
	 */
	public void onResume() {
		
	}
	
	/**
	 * 当界面消失,会做的操作
	 */
	public void onPause() {
		
	}

	/**
	 * 返回创建的view对象
	 * @return
	 */
	public View getView() {
		if(showInMiddle.getLayoutParams() == null) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			showInMiddle.setLayoutParams(params);
		}	
		return showInMiddle;
	}
	
	/**
	 * 返回自己的标识
	 */
	public abstract int getIdentifier();
	
	/**
	 * 响应监听的时间, super.onclick不要删
	 * 用UiManager跳转界面
	 * 例如:UiManager.getUiManager().changeView(TestView2.class);
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgHome:
			UiManager.getUiManager().changeView(HomeView.class, bundle);
			break;
		case R.id.imgSearch:
			UiManager.getUiManager().changeView(HomeView.class);
			break;
		case R.id.imgClassify:
			UiManager.getUiManager().changeView(CategoryView.class);
			break;
		case R.id.imgShoppingCar:
			UiManager.getUiManager().changeView(ShoppingCart.class);
			break;
		case R.id.imgMore:
			UiManager.getUiManager().changeView(MoreView.class);
			break;
		}
	}
	
	/**
	 * 查找在showInMiddle的资源
	 * @param id
	 * @return
	 */
	public View findViewById(int id) {
		return showInMiddle.findViewById(id);
	}
		
	/**
	 * 连接网络的异步类 
	 * @param <Params> 传输的参数类型
	 */
	public abstract class MyHttpAsyncTask<Params> extends 
			AsyncTask<Params, Void, String> {	
		public final AsyncTask<Params, Void, String> executeProxy(Params... params) {
			if(NetUtil.checkNet(context)) {
				return execute(params);
			} else {
				PromptManager.showNoNetWork(context);
			}
			return null;
	    }
	}
}