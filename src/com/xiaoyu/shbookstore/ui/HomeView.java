package com.xiaoyu.shbookstore.ui;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.customctrl.MyViewPager;
import com.xiaoyu.shbookstore.manager.UiManager;

public class HomeView extends BaseView {
	private ListView custonInfoListView;
	private ImageView imgIcon;
	private TextView textContent;
	// ViewPager的组件
	private MyViewPager viewPager;
	private LinearLayout pointGroup;
	protected int lastPosition;// 上一个页面

	UiManager uiManager;

	private TextView orderTelTv;
	// Handler handler;

	// ListView的数据
	private static String[] names = { "限时抢购", "促销优惠", "新书上架", "畅销书刊", "良品推荐" };
	private static int[] ids = { R.drawable.home_classify_01,
			R.drawable.home_classify_02, R.drawable.home_classify_03,
			R.drawable.home_classify_04, R.drawable.home_classify_05 };

	// ViewPager的图片
	private static final int[] imageids = {
			R.drawable.image1,R.drawable.image2, 
			R.drawable.image3, R.drawable.image4,
			R.drawable.image5 };
	
	private ArrayList<ImageView> imageList;// view图集合

	/* *
	 * 判断是否自动滚动viewPager
	 */
	// 初始化handler的标记
	private boolean isRunning = false;
	private Handler handler;

	public HomeView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {// setListener在init后执行		
		// 抢货热线
		TextView telnumber = (TextView) findViewById(R.id.orderTelTv);
		telnumber.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + 42082088));
				// UiManager.getUiManager().getActivity().startActivity(intent);
				view.getContext().startActivity(intent);
			}
		});

		/**
		 * viewPager被触摸停止滑动
		 */
		viewPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					isRunning = false;					
					break;
				case MotionEvent.ACTION_UP:
					isRunning = true;
					break;
				}
				return viewPager.onTouchEvent(event);
			}
		});
	}

	@Override
	public void init() {
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				handler.sendEmptyMessageDelayed(0, 5000);
				if (isRunning) {
					viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
				}
			};
		};
		handler.sendEmptyMessageDelayed(0, 5000);
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.home_activity, null);
		// ListView
		custonInfoListView = (ListView) findViewById(R.id.custonInfoListView);
		custonInfoListView.setAdapter(new MyAdapter());
		// ViewPager
		viewPager = (MyViewPager) findViewById(R.id.gallery);
		pointGroup = (LinearLayout) findViewById(R.id.point_group);
		viewPager.setAdapter(new MyPagerAdapter());
		// 初始化ViewPager图片资源
		imageList = new ArrayList<ImageView>();
		for (int i = 0; i < imageids.length; i++) {
			ImageView image = new ImageView(context);
			image.setBackgroundResource(imageids[i]);
			imageList.add(image);
			// 添加指示点
			ImageView point = new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.rightMargin = 5;
			point.setLayoutParams(params);
			point.setBackgroundResource(R.drawable.point_bg);
			if (i == 0) {
				point.setEnabled(true);
			} else {
				point.setEnabled(false);
			}
			pointGroup.addView(point);
		}
		// 双向滑动,把他的初始位置设置到integer中间
		viewPager.setCurrentItem(Integer.MAX_VALUE / 2
				- (Integer.MAX_VALUE / 2 % imageList.size()));
		/**
		 * viewPager的页面变化监听器
		 */
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * 页面切换后调用 
			 * position  新的页面位置
			 */
			@Override
			public void onPageSelected(int position) {
				position = position % imageids.length;// 页面轮转
				// 改变指示点的状态
				// 把当前点enbale 为true
				pointGroup.getChildAt(position).setEnabled(true);
				// 把上一个点设为false
				pointGroup.getChildAt(lastPosition).setEnabled(false);
				lastPosition = position;
			}

			@Override
			// 页面正在滑动的时候，回调
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			// 当页面状态发生变化的时候，回调
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	@Override
	public void onResume() {
		isRunning = true;
		super.onResume();
	}

	@Override
	public void onPause() {
		isRunning = false;
		super.onPause();
	}

	/**
	 * ViewPager的适配器
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			object = null;
			// ImageView ivImage = imageList.get(position);
			// container.removeView(ivImage);
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			ImageView img = imageList.get(position % imageList.size());
			// ImageView img=imageList.get(position);
			container.addView(img);
			/**
			 * 每一个image的点击事件
			 */
			img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					uiManager = UiManager.getUiManager();
					switch (position % imageList.size()) {
					case 0:
						uiManager.changeView(ShoppingCart.class);
						break;
					case 1:
						uiManager.changeView(ShoppingCart.class);
						break;
					case 2:
						uiManager.changeView(ShoppingCart.class);
						break;
					case 3:
						uiManager.changeView(ShoppingCart.class);
						break;
					case 4:
						uiManager.changeView(ShoppingCart.class);
						break;
					default:
						break;
					}
				}
			});
			return img;
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;//为了让ListView无限旋转,把次数搞到最大???
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

	/**
	 * ListView的适配器
	 * 
	 */
	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return ids.length;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View items;
			// 填充item
			items = View.inflate(context, R.layout.home_item, null);
			imgIcon = (ImageView) items.findViewById(R.id.imgIcon);
			textContent = (TextView) items.findViewById(R.id.textContent);
			imgIcon.setImageResource(ids[position]);
			textContent.setText(names[position]);
			items.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					uiManager = UiManager.getUiManager();
					switch (position) {
					case 0:
						uiManager.changeView(LimitBuyView.class);
						break;
					case 1:
						uiManager.changeView(TopicView.class);
						break;
					case 2:
						uiManager.changeView(NewProductView.class);
						break;
					case 3:
						uiManager.changeView(HotProductView.class);
						break;
					case 4:
						uiManager.changeView(RecommendBrandView.class);
						break;
					default:
						break;
					}
				}
			});
			return items;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_HOME;
	}
}
