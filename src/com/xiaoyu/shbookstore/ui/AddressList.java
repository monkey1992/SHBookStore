package com.xiaoyu.shbookstore.ui;


import com.xiaoyu.shbookstore.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class AddressList extends BaseView {
	
	private ListView address_list_list;
	
	public AddressList(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		  
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.address_list_activity, null);
		address_list_list =(ListView) showInMiddle.findViewById(R.id.address_list_list);
		
		MyAdapter adapter = new MyAdapter();
		address_list_list.setAdapter(adapter);
	}
	
	/**
	 * 给地址列表定义适配器
	 *
	 */
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 0;
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
			return null;
		}
	}


	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}

}












