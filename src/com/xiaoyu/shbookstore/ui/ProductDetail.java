package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.image.SmartImageView;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.Cart;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.manager.PromptManager;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class ProductDetail extends BaseView {
	
	private Gallery mGallery;
	private TextView mProductName;
	private TextView mProductId;
	private TextView mOriginalPriceValue;
	private TextView mtextPriceValue;
	private EditText mProdNumValue;
	private TextView mPutIntoShopcar;
	private TextView mProdToCollect;
	
	private TextView mCommentNum;
	
	private MyAdapter adapter;
	
	private Product product;
	
	public ProductDetail(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		mProdNumValue.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				/*if(product == null || !StringUtils.isNotBlank(mProdNumValue.getText().toString())
					|| Integer.parseInt(mProdNumValue.getText().toString()) <=0
						) {
					return;
				}*/
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(!StringUtils.isNotBlank(mProdNumValue.getText().toString())){
					return;
				}else if(Integer.parseInt(mProdNumValue.getText().toString()) <=0) {
					
					mProdNumValue.setText("1");
					
				}else if(Integer.parseInt(mProdNumValue.getText().toString()) > product.getBuyLimit()) {
						PromptManager.showToast(context, "商品库存为:"+product.getBuyLimit());
						mProdNumValue.setText(String.valueOf(product.getBuyLimit()));
						
				}
				int productNumber = Integer.parseInt(mProdNumValue.getText().toString());
				int totalPrice = productNumber * Integer.parseInt(product.getPrice());
				System.out.println("num:"+productNumber+",totalPrice:"+totalPrice);
				product.setTotalPrice(String.valueOf(totalPrice));
				product.setProdAccount(String.valueOf(productNumber));
//				int productNumber = Integer.parseInt(mProdNumValue.getText().toString());
//				int totalPrice = productNumber * Integer.parseInt(mtextPriceValue.getText().toString());
//				mtextPriceValue.setText(String.valueOf(totalPrice));
			}
		});
		
		mPutIntoShopcar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(GlobalParams.isLogin) {
					if(product != null) {
						if(!StringUtils.isNotBlank(mProdNumValue.getText().toString())){
							PromptManager.showToast(context, "请填写正确的数量");
							return;
						}
						boolean add = Cart.getCart().getProduct().add(product);
						if(add) {
							PromptManager.showToast(context, "加入购物车成功");
						}else {
							PromptManager.showToast(context, "请稍候再试");
						}
					}
				}else {
					UiManager.getUiManager().changeView(LoginView.class);
					PromptManager.showToast(context, "请先登录");
				}
				
			}
		});
		
		mProdToCollect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(GlobalParams.isLogin){
					if(product != null) {
						new MyHttpAsyncTask<Void>() {
							@Override
							protected String doInBackground(Void... params) {
								HttpClientUtil client = new HttpClientUtil();
								Map<String,String> data = new HashMap<String, String>();
								data.put("pId", String.valueOf(product.getPro_id()));
								String rJson = client.sendGet(ConstantValue.COMMON_URI.concat(ConstantValue.PRODUCT_FAVORITES), data);
								return rJson;
							}

							@Override
							protected void onPostExecute(String result) {
								if(result != null) {
									try {
										JSONObject obj = new JSONObject(result);
										if("error".equals(obj.getString("response"))) {
											PromptManager.showToast(context, obj.getString("response"));
										}else {
											PromptManager.showToast(context, "收藏成功");
										}
									} catch (JSONException e) {
										e.printStackTrace();
									}
								}else {
									PromptManager.showToast(context, "服务器正忙...");
								}
								
								super.onPostExecute(result);
							}
							
						}.executeProxy();
					}
				}else {
					PromptManager.showToast(context, "请先登录");
				}
			}
		});
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.product_detail_activity, null);
		mGallery = (Gallery) findViewById(R.id.productGallery);
		mProductName = (TextView) findViewById(R.id.textProductNameValue);
		mProductId = (TextView) findViewById(R.id.textProductIdValue);
		mOriginalPriceValue = (TextView) findViewById(R.id.textOriginalPriceValue);
		mtextPriceValue = (TextView) findViewById(R.id.textPriceValue);
		mProdNumValue = (EditText) findViewById(R.id.prodNumValue);
		mPutIntoShopcar =  (TextView) findViewById(R.id.textPutIntoShopcar);
		mProdToCollect = (TextView) findViewById(R.id.textProdToCollect);
		mCommentNum = (TextView) findViewById(R.id.textProductCommentNum);
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_PRODUCT_DETAIL;
	}
	
	
	private Map<String,String> params;
	@Override
	public void onResume() {
		if(bundle != null) {
			String productId = bundle.getString("productid");
			params = new HashMap<String, String>();
			params.put("pId", productId);
			getProductInfo(params);
		}
		super.onResume();
	}
	
	private void getProductInfo(Map<String,String> params) {
		new MyHttpAsyncTask<Map>() {

			@Override
			protected String doInBackground(Map... params) {
				HttpClientUtil client = new HttpClientUtil();
				String json = client.sendGet(ConstantValue.COMMON_URI.concat(ConstantValue.PRODUCTINFO), params[0]);
				return json;
			}

			@Override
			protected void onPostExecute(String result) {
				if(result != null) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						product = JSON.parseObject(jsonObject.getString("product"),Product.class);
						if(product != null) {
							adapter = new MyAdapter();
							mGallery.setAdapter(adapter);
							mProductId.setText(String.valueOf(product.getPro_id()));
							mProductName.setText(product.getName());
							mOriginalPriceValue.setText(product.getMarketPrice());
							mtextPriceValue.setText(product.getPrice());
							mProdNumValue.setText("1");
							mCommentNum.setText(product.getCommentCount());
							product.setTotalPrice(product.getPrice());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				super.onPostExecute(result);
			}
			
			
		}.executeProxy(params);
	}
	
	
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 1;
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
			SmartImageView siv = null;
			if(convertView == null) {
				convertView = new SmartImageView(context);
			}
			String url = product.getPic();
			((SmartImageView)convertView).setImageUrl(url);
			
			return convertView;
		}
	}
	
}
