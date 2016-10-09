package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.manager.UiManager;
/**
 * 推荐品牌界面
 */
public class RecommendBrandView extends BaseView {

	public RecommendBrandView(Context context) {
		super(context);

	}
	//private ExpandableListView listBrandInfo; 
	//private MyBaseExpandableListAdapter adapter;
	private TextView textTitle;
	private TextView textParent1;
	private TextView textParent2;
	private TextView textParent3;
	private GridView nineGv1;
	private GridView nineGv2;
	private GridView nineGv3;
	private GridAdapter1 adapter1;
	private GridAdapter2 adapter2;
	private GridAdapter3 adapter3;
	private ImageView brandIconIv;
	private boolean isNineGv1Open = false;
	private boolean isNineGv2Open = false;
	private boolean isNineGv3Open = false;
	private TextView backTv;
	//子视图图片           
	int[] imgs = {R.drawable.book_kepu1,R.drawable.book_kepu2, R.drawable.book_kepu3,R.drawable.book_kepu4,
			R.drawable.book_kepu5, R.drawable.book_kepu6, R.drawable.book_kepu7};
		
	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.recommendbrand_activity, null);
		textTitle = (TextView) findViewById(R.id.textTitle);
		textTitle.setText("良品推荐");
		//listBrandInfo = (ExpandableListView) findViewById(R.id.listBrandInfo);
		//adapter = new MyBaseExpandableListAdapter();
		//listBrandInfo.setAdapter(adapter);
		textParent1 = (TextView) findViewById(R.id.textParent1);
		textParent2 = (TextView) findViewById(R.id.textParent2);
		textParent3 = (TextView) findViewById(R.id.textParent3);
		adapter1 = new GridAdapter1();
		adapter2 = new GridAdapter2();
		adapter3 = new GridAdapter3();
		backTv = (TextView) findViewById(R.id.backTv);
		textParent1.setText("科普类");
		nineGv1 = (GridView) findViewById(R.id.nineGv1);
		nineGv1.setAdapter(adapter1);
		textParent2.setText("文学类");
		nineGv2 = (GridView) findViewById(R.id.nineGv2);
		nineGv2.setAdapter(adapter2);
		textParent3.setText("艺术类");
		nineGv3 = (GridView) findViewById(R.id.nineGv3);
		nineGv3.setAdapter(adapter3);
	}
	
	@Override
	public void setListener() {
		backTv.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				UiManager.getUiManager().goBack();
			}
		});	
		textParent1.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(isNineGv1Open){
					nineGv1.setVisibility(View.GONE);
					isNineGv1Open = false;
				}else{
					nineGv1.setVisibility(View.VISIBLE);
					isNineGv1Open = true;
				}
			}
		});		
		textParent2.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				if(isNineGv2Open){
					nineGv2.setVisibility(View.GONE);
					isNineGv2Open = false;
				}else{
					nineGv2.setVisibility(View.VISIBLE);
					isNineGv2Open = true;
				}
			}
		});		
		textParent3.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				if(isNineGv3Open){
					nineGv3.setVisibility(View.GONE);
					isNineGv3Open = false;
				}else{
					nineGv3.setVisibility(View.VISIBLE);
					isNineGv3Open = true;
				}
			}
		});		
		nineGv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				UiManager.getUiManager().changeView(BrandProductListView.class);
			}
		});		
		nineGv2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				UiManager.getUiManager().changeView(BrandProductListView.class);
			}
		});	
		nineGv3.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				UiManager.getUiManager().changeView(BrandProductListView.class);
			}
		});
	}
		
	private class GridAdapter1 extends BaseAdapter{
		@Override
		public int getCount() {
			return 7;
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
			ViewHolder holder=null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.brand_child_item, null);
				holder.brandIconIv = (ImageView) convertView.findViewById(R.id.brandIconIv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.brandIconIv.setImageResource(imgs[position]);
			return convertView;
		}
		class ViewHolder{
			ImageView brandIconIv;
		}
	}
   
	private class GridAdapter2 extends BaseAdapter{
		@Override
		public int getCount() {
			return 7;
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
			ViewHolder holder=null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.brand_child_item, null);
				holder.brandIconIv = (ImageView) convertView.findViewById(R.id.brandIconIv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.brandIconIv.setImageResource(imgs[position]);
			return convertView;
		}
		class ViewHolder{
			ImageView brandIconIv;
		}
		
	}
	
	private class GridAdapter3 extends BaseAdapter{
		@Override
		public int getCount() {
			return 7;
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
			ViewHolder holder=null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.brand_child_item, null);
				holder.brandIconIv = (ImageView) convertView.findViewById(R.id.brandIconIv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.brandIconIv.setImageResource(imgs[position]);
			return convertView;
		}
		class ViewHolder{
			ImageView brandIconIv;
		}
		
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.RECOMMENDBRANDVIEW;
	}

}
