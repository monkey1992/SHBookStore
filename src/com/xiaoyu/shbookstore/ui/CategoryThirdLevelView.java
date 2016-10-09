package com.xiaoyu.shbookstore.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.Category;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class CategoryThirdLevelView extends BaseView {

	private List<Category> parseArray;
	private Category product;

	private Bundle categoryBundle;
	private int parentId;
	MyAdapter adapter;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (adapter == null) {
				adapter = new MyAdapter();
				categoryList.setAdapter(adapter);
			} else {
				adapter.notifyDataSetChanged();
			}
		}

	};

	public CategoryThirdLevelView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub

	}

	private ListView categoryList;
	Map<String, String> params;
	
	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.category_child_activity, null);
		categoryList = (ListView) showInMiddle.findViewById(R.id.categoryList);

		params = new HashMap<String, String>();
		params.put("version", "1.0");
		
	}
	
	/**
	 * 子孩子集合
	 */
	private List<Category> categoryNames = new ArrayList<Category>();
	
	/**
	 * 从服务器获取分类信息
	 */
	private void getData() {
		new MyHttpAsyncTask<Map>() {

			@Override
			protected String doInBackground(Map... params) {
				// 获取数据——业务的调用
				HttpClientUtil client = new HttpClientUtil();
				String result = client.sendGet(ConstantValue.CATEGORY,
						params[0]);

				return result;
			}

			@Override
			protected void onPostExecute(String result) {
				// 解析数据
				try {
					JSONObject js = new JSONObject(result);
					parseArray = JSON.parseArray(js.getString("category"),
							Category.class);

					for (Category c : parseArray) {
						if (c.getParentId() == 6) {
							categoryNames.add(c);// 类名
						}
					}
					
					if (adapter == null) {
						adapter = new MyAdapter();
						categoryList.setAdapter(adapter);
					} else {
						adapter.notifyDataSetChanged();
					}

					//handler.sendEmptyMessage(0);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.executeProxy(params);

	}
	
	@Override
	public void onResume() {
		if (categoryNames.size() > 0) {
			categoryNames.clear();
		}
		getData();
	}

	@Override
	public void onPause() {
	}
	
	
	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int[] ids = new int[]{R.drawable.category1,R.drawable.category2,R.drawable.category3,R.drawable.category4,R.drawable.category5};
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return categoryNames.size();
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

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			product = categoryNames.get(position);
			View view;
			ViewHolder holder;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				holder = new ViewHolder();
				view = View.inflate(context, R.layout.category_item, null);
				holder.icon = (ImageView) view.findViewById(R.id.imgIcon);
				holder.content = (TextView) view.findViewById(R.id.textContent);
				holder.describe = (TextView) view
						.findViewById(R.id.item_describe);
				view.setTag(holder);
			}
			holder.icon.setImageResource(ids[position%5]);
			holder.content.setText(product.getName());
			holder.describe.setText(product.getTag());

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					/*UiManager uiManager = UiManager.getUiManager();
					categoryBundle = new Bundle();

					categoryBundle.putString("listType", "category");
					categoryBundle.putString("cId",String.valueOf(categoryNames.get(position).getId()));
*/
					UiManager.getUiManager().changeView(BrandProductListView.class);
					
				}
			});

			return view;
		}

		class ViewHolder {
			ImageView icon;
			TextView content;
			TextView describe;
		}
	}

}
