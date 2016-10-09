package com.xiaoyu.shbookstore.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.ui.BaseView;
import com.xiaoyu.shbookstore.util.SoftMap;

public class UiManager{
	private static UiManager uiManager = new UiManager();
	
	/**
	 * 缓存界面的集合
	 */
	public static Map<String, BaseView> VIEWCACHE;
	
	static {
		//大于16M充足,否则内存不足,真机一般32M
		if(MemoryManager.hasAcailMemory()) {
			//当内存充足的时候
			VIEWCACHE = new HashMap<String, BaseView>();
		}else {
			//当内存不足的时候
			VIEWCACHE = new SoftMap<String, BaseView>();
		}
	}
	
	private Activity mainActivity;
	
	/**
	 * 记录用户操作的顺序集合
	 */
	private LinkedList<String> HISTORY = new LinkedList<String>();
	
	/**
	 * 显示中间内容的容器
	 */
	private RelativeLayout middleContainer;
	
	/**
	 * 当前显示界面
	 */
	private BaseView currentBaseView;
	
	/**
	 * 通过单例的方式实例化UiManager
	 * @return UiManager
	 */
	public static UiManager getUiManager() {
		return uiManager;
	}
	
	private UiManager() {
		
	}
	
	/**
	 * 初始化每个页面的中间部分
	 * @param middleContainder
	 * 容器
	 * @param mainActivity
	 * MainActivity
	 */
	public void initMiddleContainer(RelativeLayout middleContainder, Activity mainActivity) {
		this.middleContainer = middleContainder;
		this.mainActivity = mainActivity;
	}
		
	public Activity getActivity() {
		return mainActivity;
	}
	
	public boolean changeView(Class<? extends BaseView> targetViewClazz, Bundle bundle) {
		if(currentBaseView != null && currentBaseView.getClass() == targetViewClazz) {
			return false;
		}		
		String key = targetViewClazz.getSimpleName();
		BaseView cacheView = null;
		if(VIEWCACHE.containsKey(key) && StringUtils.isNotBlank(key)) {
			cacheView = VIEWCACHE.get(key);
		}else {
			try {
				cacheView = targetViewClazz.getConstructor(Context.class).newInstance(getContext());
				VIEWCACHE.put(key, cacheView);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//在清理当前正在移除界面之前 , 针对自己做清理工作
		if(currentBaseView != null) {
			currentBaseView.onPause();
			//AnimationController.fadeOut(currentBaseView.getView(), 500, 0);
		}		
		if(cacheView != null && bundle != null) {
			cacheView.setBundle(bundle);
		}			
		middleContainer.removeAllViews();	
		HISTORY.addFirst(key);		
		middleContainer.addView(cacheView.getView());
		//加载界面后做的操作
		cacheView.onResume();		
		if(currentBaseView != null) { 
			GlobalParams.preViewId = currentBaseView.getIdentifier();
		}	
		currentBaseView = cacheView;		
		//notifyTitleAndBottom(cacheView);
		return true;
	}
	
	public boolean changeView(Class<? extends BaseView> targetViewClazz) {
		if("HomeView".equals(targetViewClazz.getSimpleName())){
			HISTORY.removeAll(HISTORY);
		}
		return changeView(targetViewClazz, null);
	}
	
	/*public void notifyTitleAndBottom(BaseView cacheView) {
		setChanged();
		notifyObservers(cacheView.getIdentifier());
	}*/
	
	public Context getContext() {
		return middleContainer.getContext();
	}
	
	public BaseView getCurrentView() {
		return currentBaseView;
	}
	
	/**
	 * 切换显示界面
	 * @return
	 */
	public boolean changeOperations() {
		return goBack();
	}
	
	/**
	 * 返回键
	 * @return
	 */
	public boolean goBack() {
		// 记录用户操作历史
		// 获取栈顶
		// 有序集合
		if (HISTORY.size() > 0) {
			//当用户误操作返回键（不退出出应用）
			//此时用户操作的界面为首页
			if (HISTORY.size() == 1) {
				return false;
			}
			// Throws:NoSuchElementException - if this LinkedList is empty.
			if(currentBaseView != null) {
				currentBaseView.onPause();
			}			
			if(!HISTORY.isEmpty()) {
				HISTORY.removeFirst();
			}
			if (HISTORY.size() > 0) {
				// Throws:NoSuchElementException - if this LinkedList is empty.
				String key = HISTORY.getFirst();//得到当前界面的上一个界面
				BaseView targetUI = VIEWCACHE.get(key);
				if(targetUI != null) {
					middleContainer.removeAllViews();
					middleContainer.addView(targetUI.getView());
					targetUI.onResume();
					currentBaseView = targetUI;
//					notifyTitleAndBottom(currentBaseView);
				}else {
//					changeView(Hall.class);  
				}
				return true;
			}
		}
		return false;
	}
	
	public void clearOperations() {
		HISTORY.clear();
	}
}
