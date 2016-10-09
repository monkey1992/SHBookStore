package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.engine.TopicAndBrandEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

/**
 * 限时抢购界面
 */
public class LimitBuyView extends BaseView {
	private TextView backTv;
	private ListView productList;
	private MyAdapter adapter;
	private List<Product> limitBuyProductListFromNet;
	private Product product;
	private TextView textNull;
	private TopicAndBrandEngine engine;
	private Map<String, String> params;
	private long countDownTime;

	public LimitBuyView(Context context) {
		super(context);
	}

	@Override
	public void onResume() {
		countDownTime = 0;
		fillData();
		super.onResume();
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.limit_buy_activity, null);
		backTv = (TextView) findViewById(R.id.backTv);
		productList = (ListView) findViewById(R.id.productList);
		bundle = new Bundle();
		engine = BeanFactory.getImpl(TopicAndBrandEngine.class);
		params = new HashMap<String, String>();
		params.put("page", "1");
		params.put("pageNum", "5");
		textNull = (TextView) findViewById(R.id.textNull);
	}

	@Override
	public void setListener() {
		backTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UiManager.getUiManager().goBack();
			}
		});
		productList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				bundle.putString("productid", String
						.valueOf(limitBuyProductListFromNet.get(position)
								.getId()));
				UiManager.getUiManager()
						.changeView(ProductDetail.class, bundle);
			}
		});
	}

	private void fillData() {
		new MyHttpAsyncTask<Void>() {
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if (limitBuyProductListFromNet != null) {
					if (adapter == null) {
						adapter = new MyAdapter();
						productList.setAdapter(adapter);
						new MyCountDownTimer(Long.MAX_VALUE, 1000).start();
					} else {
						adapter.notifyDataSetChanged();
					}
				} else {
					productList.setVisibility(View.INVISIBLE);
					textNull.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
			
			@Override
			protected String doInBackground(Void... params1) {
				limitBuyProductListFromNet = engine
						.getLimitBuyProductListFromNet(ConstantValue.COMMON_URI
								.concat(ConstantValue.LIMITBUY), params);
				return null;
			}
		}.executeProxy();
	}

	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return limitBuyProductListFromNet.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.limit_buy_item,
						null);
				holder.goodsIconIv = (SmartImageView) convertView
						.findViewById(R.id.goodsIconIv);
				holder.iv_limit_buy_arrow = (ImageView) convertView
						.findViewById(R.id.iv_limit_buy_arrow);
				holder.textClothesName = (TextView) convertView
						.findViewById(R.id.textClothesName);
				holder.tv_limit_buy_marketPrice = (TextView) convertView
						.findViewById(R.id.tv_limit_buy_marketPrice);
				holder.limitPrice = (TextView) convertView
						.findViewById(R.id.limitPrice);
				holder.textClothesPrice = (TextView) convertView
						.findViewById(R.id.textClothesPrice);
				holder.textLeftTime = (TextView) convertView
						.findViewById(R.id.textLeftTime);
				holder.tv_limit_buy_AddToCart = (Button) convertView
						.findViewById(R.id.tv_limit_buy_AddToCart);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			product = limitBuyProductListFromNet.get(position);
			holder.goodsIconIv.setImageUrl(product.getPic());
			holder.textClothesName.setText(product.getName());
			holder.tv_limit_buy_marketPrice.setText("市场价￥"
					+ product.getMarketPrice() + "元");
			holder.limitPrice.setText("抢购价￥" + product.getLimitprice() + "元");
			holder.textClothesPrice.setText("原价￥" + product.getPrice() + "元");
			holder.textLeftTime.setText("剩余时间:"
					+ getLasttime(String.valueOf(Long.valueOf(product
							.getLefttime()) - countDownTime)));
			holder.textLeftTime.setTag(position);
			holder.tv_limit_buy_AddToCart
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							UiManager.getUiManager().changeView(
									ShoppingCart.class, bundle);
						}
					});
			return convertView;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		private class ViewHolder {
			SmartImageView goodsIconIv;
			ImageView iv_limit_buy_arrow;
			TextView textClothesName;
			TextView tv_limit_buy_marketPrice;
			TextView limitPrice;
			TextView textClothesPrice;
			TextView textLeftTime;
			Button tv_limit_buy_AddToCart;
			
			public View getTag() {
				return null;
			}
		}
	}

	class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}
		
		@Override
		public void onTick(long millisUntilFinished) {
			countDownTime += 1;
			TextView timetv;
			for (int i = 0; i < (limitBuyProductListFromNet == null ? 0
					: limitBuyProductListFromNet.size()); i++) {
				timetv = (TextView) productList.findViewWithTag(i);
				if (timetv != null) {
					timetv.setText("剩余时间:"
							+ getLasttime(String.valueOf(Long
									.valueOf(limitBuyProductListFromNet.get(i)
											.getLefttime())
									- countDownTime)));
				}
			}
		}

		@Override
		public void onFinish() {
			
		}
	}

	// {"productlist":[{"id":"1200002","price":"66","marketprice":"66","name":"雅培银装a","pic":"http://192.168.1.26/ECService/image/product/product_02s.jpg","limitprice":"88","lefttime":"3600"},{"id":"1200003","price":"88","marketprice":"99","name":"贝因美优选","pic":"http://192.168.1.26/ECService/image/product/product_03s.jpg","limitprice":"66","lefttime":"7200"},{"id":"1200004","price":"78","marketprice":"79","name":"雅培银装a","pic":"http://192.168.1.26/ECService/image/product/product_04s.jpg","limitprice":"78","lefttime":"7200"},{"id":"1200005","price":"66","marketprice":"66","name":"雅培铜装a","pic":"http://192.168.1.26/ECService/image/product/product_05s.jpg","limitprice":"88","lefttime":"3600"},{"id":"1200006","price":"88","marketprice":"99","name":"优选金装","pic":"http://192.168.1.26/ECService/image/product/product_06s.jpg","limitprice":"66","lefttime":"7200"}],"response":"limitbuy","list_count":6}
	/**
	 * 将秒时间转换成日时分格式
	 * 
	 * @param lasttime
	 * @return
	 */
	public String getLasttime(String lasttime) {
		StringBuffer result = new StringBuffer();
		if (StringUtils.isNumericSpace(lasttime)) {
			int time = Integer.parseInt(lasttime);
			int hour = time / 3600;
			result.append(hour).append("时");
			if (hour > 0) {
				time = time - hour * 60 * 60;
			}
			int minute = time / 60;
			if (minute > 0) {
				time = time - minute * 60;
			}
			result.append(minute).append("分");
			int second = time;
			result.append(second).append("秒");
		}
		return result.toString();
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.LIMITBUYVIEW;
	}
}
