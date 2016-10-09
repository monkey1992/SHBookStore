package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
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
 * 新品上架界面
 */
public class NewProductView extends BaseView {
	private ListView productLv;
	private MyAdapter adapter;
	private List<Product> productListFromNet;
	private Product product;
	private TextView textTitle;
	private TextView backTv;
	private TextView textNull;
	private TopicAndBrandEngine engine;
	private Map<String,String> params;
	private Button textRankSale;
	private Button textRankPrice;
	private Button textRankGood;
	private Button textRankTime;
	private boolean isClicked;
	public NewProductView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.product_list_activity, null);
		productLv = (ListView) findViewById(R.id.lv_productlist_items);
		bundle = new Bundle();
		textTitle = (TextView) findViewById(R.id.textTitle);
		textTitle.setText("新书上架");
		backTv = (TextView) findViewById(R.id.backTv);
		textRankSale = (Button) findViewById(R.id.btn_order_productlist01);
		textRankPrice = (Button) findViewById(R.id.btn_order_productlist02);
		textRankGood = (Button) findViewById(R.id.btn_order_productlist03);
		textRankTime = (Button) findViewById(R.id.btn_order_productlist04);
		engine = BeanFactory.getImpl(TopicAndBrandEngine.class);
		params = new HashMap<String,String>();
		params.put("page", "1");
		params.put("pageNum","5");
		params.put("id", "1");
		params.put("orderby","sale_down");
		textNull = (TextView) findViewById(R.id.textNull);
		fillData();
	}
	
	@Override
	public void setListener() {
		backTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UiManager.getUiManager().goBack();
			}
		});	
		productLv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				bundle.putString("productid", String.valueOf(productListFromNet.get(position).getId()));
				UiManager.getUiManager().changeView(ProductDetail.class, bundle);
			}
		});	
		textRankSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				params.put("orderby", "sale_down");
				new MyHttpAsyncTask<Void>() {			
					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						if(productListFromNet != null){						
							if(adapter == null){
								adapter = new MyAdapter();
								productLv.setAdapter(adapter);
							}else{
								adapter.notifyDataSetChanged();
							}
						}else{
							productLv.setVisibility(View.INVISIBLE);
							textNull.setVisibility(View.VISIBLE);
						}						
					}	
				
					@Override
					protected void onPreExecute() {			
						super.onPreExecute();
					}

					@Override
					protected String doInBackground(Void... params1) {
						productListFromNet = engine.getNewProductListFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.NEWPRODUCT), params);
						return null;
					}				
				}.executeProxy();
			}
		});
		
		textRankPrice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isClicked = false;
				if(isClicked){
					params.put("orderby", "price_up");
					isClicked = false;
				}else{
					params.put("orderby", "price_down");
					isClicked = true;
				}
				new MyHttpAsyncTask<Void>() {			
					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						if(productListFromNet != null){						
							if(adapter == null){
								adapter = new MyAdapter();
								productLv.setAdapter(adapter);
							}else{
								adapter.notifyDataSetChanged();
							}
						}else{
							productLv.setVisibility(View.INVISIBLE);
							textNull.setVisibility(View.VISIBLE);
						}					
					}				
					
					@Override
					protected void onPreExecute() {					
						super.onPreExecute();
					}

					@Override
					protected String doInBackground(Void... params1) {
						productListFromNet = engine.getNewProductListFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.NEWPRODUCT), params);
						return null;
					}				
				}.executeProxy();
			}
		});
		textRankGood.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				params.put("orderby", "comment_down");
				new MyHttpAsyncTask<Void>() {				
					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						if(productListFromNet != null){						
							if(adapter == null){
								adapter = new MyAdapter();
								productLv.setAdapter(adapter);
							}else{
								adapter.notifyDataSetChanged();
							}
						}else{
							productLv.setVisibility(View.INVISIBLE);
							textNull.setVisibility(View.VISIBLE);
						}						
					}	
					
					@Override
					protected void onPreExecute() {		
						super.onPreExecute();
					}

					@Override
					protected String doInBackground(Void... params1) {
						productListFromNet = engine.getNewProductListFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.NEWPRODUCT), params);
						return null;
					}			
				}.executeProxy();
			}
		});
		textRankTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				params.put("orderby", "shelves_down");
				new MyHttpAsyncTask<Void>() {		
					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						if(productListFromNet != null){	
							if(adapter == null){
								adapter = new MyAdapter();
								productLv.setAdapter(adapter);
							}else{
								adapter.notifyDataSetChanged();
							}
						}else{
							productLv.setVisibility(View.INVISIBLE);
							textNull.setVisibility(View.VISIBLE);
						}
					}	
					
					@Override
					protected void onPreExecute() {
						super.onPreExecute();
					}

					@Override
					protected String doInBackground(Void... params1) {
						productListFromNet = engine.getNewProductListFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.NEWPRODUCT), params);
						return null;
					}	
				}.executeProxy();
			}
		});
	}
		
	private void fillData(){
		new MyHttpAsyncTask<Void>() {
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if(productListFromNet != null){
					
					if(adapter == null){
						adapter = new MyAdapter();
						productLv.setAdapter(adapter);
					}else{
						adapter.notifyDataSetChanged();
					}
				}else{
					productLv.setVisibility(View.INVISIBLE);
					textNull.setVisibility(View.VISIBLE);
				}	
			}	
				
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(Void... params1) {
				productListFromNet = engine.getNewProductListFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.NEWPRODUCT), params);
				return null;
			}
		}.executeProxy();
	}
	
	class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return productListFromNet.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.product_list_items, null);
				holder.textClothesName = (TextView) convertView.findViewById(R.id.textClothesName);
				holder.textClothesPrice = (TextView) convertView.findViewById(R.id.textClothesPrice);
				holder.textMarketPrice = (TextView) convertView.findViewById(R.id.textMarketPrice);
				holder.goodsIconIv =  (SmartImageView) convertView.findViewById(R.id.goodsIconIv);
				holder.imgIsNew = (ImageView) convertView.findViewById(R.id.imgIsNew);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();//若缓存不为空就从convertView的tag中拿出那些存进去的标记作为holder
			}
			product = productListFromNet.get(position);
			holder.textClothesName.setText(product.getName());
			holder.textClothesPrice.setText("商城价:￥"+product.getPrice()+"元");
			holder.textMarketPrice.setText("市场价:￥"+product.getMarketPrice()+"元");
			holder.imgIsNew.setVisibility(View.VISIBLE);
			//holder.goodsIconIv.setImageURI(Uri.parse(product.getPic()));
//			try { 
//		        URL url = new URL(product.getPic()); 
//		        holder.goodsIconIv.setImageBitmap(BitmapFactory.decodeStream(url.openStream())); 
//		    } catch (Exception e) { 
//		        		e.printStackTrace();
//		    } 
			holder.goodsIconIv.setImageUrl(product.getPic());
			return convertView;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		class ViewHolder{
			private SmartImageView goodsIconIv;
			private TextView textClothesName;
			private TextView textClothesPrice;
			private TextView textMarketPrice;
			private ImageView imgIsNew;
		}
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.NEWPRODUCTVIEW;
	}	
}
