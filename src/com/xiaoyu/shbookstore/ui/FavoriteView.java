package com.xiaoyu.shbookstore.ui;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.engine.AccountEngine;
import com.xiaoyu.shbookstore.manager.PromptManager;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class FavoriteView extends BaseView {
	
	private LinearLayout myfavorite_productlist_layout;
	private ListView lvFavorite;
	private MyAdapter adapter;
	private List<Product> favorite;
	private AccountEngine ae;
	private LinearLayout emptyImage;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(favorite.size()>0) {
				adapter = new MyAdapter();
				lvFavorite.setAdapter(adapter);
			}else {
				showEmpty();
			}
		}
		
	};
	
	
	public FavoriteView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {

	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.my_favorite_activity, null);
		myfavorite_productlist_layout = (LinearLayout) findViewById(R.id.myfavorite_productlist_layout);
		lvFavorite = (ListView) findViewById(R.id.myfavorite_product_list);
		emptyImage = (LinearLayout) findViewById(R.id.favorite_empty_img);
		ae = BeanFactory.getImpl(AccountEngine.class);
	}
	
	private void showEmpty() {
		lvFavorite.setVisibility(View.GONE);
		emptyImage.setVisibility(View.VISIBLE);
	}
	
	
	private void loadData() {
		new MyHttpAsyncTask<String>() {
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				PromptManager.showProgressDialog(context);
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(favorite != null) {
					handler.sendEmptyMessage(0);
				}else {
					PromptManager.showToast(context, "更新失败");
					showEmpty();
				}
			}

			@Override
			protected String doInBackground(String... params) {
				favorite = ae.getFavorite(params[0], params[1]);
				return null;
			}		
		}.executeProxy("1","10");
		
	}

	
	@Override
	public void onResume() {
		super.onResume();
		if(GlobalParams.isLogin) {
			loadData();
		}else {
			PromptManager.showToast(context,"请登录");
			UiManager.getUiManager().changeView(LoginView.class);
		}
		
		
	}
	
	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return favorite.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null) {
				convertView = View.inflate(context, R.layout.my_favorite_listitem, null);
				holder = new ViewHolder();
				holder.siv = (SmartImageView) convertView.findViewById(R.id.myfavorite_product_img);
				holder.tvTitle =  (TextView) convertView.findViewById(R.id.myfavorite_title_text);
				holder.tvPrice = (TextView) convertView.findViewById(R.id.myfavorite_price_text);
				holder.tvDeleteprice =  (TextView) convertView.findViewById(R.id.myfavorite_deleteprice_text);
				holder.tvNostock =  (TextView) convertView.findViewById(R.id.myfavorite_nostock_text);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			Product product = favorite.get(position);
			holder.siv.setImageUrl(product.getPic());
			holder.tvTitle.setText(product.getName());
			holder.tvPrice.setText(product.getPrice());
			holder.tvDeleteprice.setText(product.getMarketPrice());
//			holder.tvNostock.setText(product.get)
			return convertView;
		}
	}

	
	class ViewHolder {
		SmartImageView siv;
		TextView tvTitle;
		TextView tvPrice;
		TextView tvDeleteprice;
		TextView tvNostock;
	}
	
	public int getIdentifier() {
		return ConstantValue.VIEW_FAVORITE;
	}

}
