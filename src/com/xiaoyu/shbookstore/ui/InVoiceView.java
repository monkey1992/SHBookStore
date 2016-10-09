package com.xiaoyu.shbookstore.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.manager.UiManager;

public class InVoiceView extends BaseView {

	private TextView head_back_text;
	private TextView invoice_save_text;
	private EditText invoice_title_edit;
	private PopupWindow popWin;
	private LinearLayout invoice_layout;
	private ListView listView;
	//private Spinner sp;
	private List<String> msgList;
	private TextView invoice_content_text;
	//private DBHelper helper;
	
	private SharedPreferences sp;
	public InVoiceView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		head_back_text.setOnClickListener(this);
		invoice_save_text.setOnClickListener(this);
		invoice_layout.setOnClickListener(this);
		//sp.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_back_text:
			UiManager.getUiManager().changeView(PayCenter.class);
			break;
			
		case R.id.invoice_save_text:
				if (!StringUtils.isEmpty(invoice_title_edit.getText())) {
					if(!StringUtils.isEmpty(invoice_content_text.getText())){
						Editor edit = sp.edit();
						edit.putString("content", (String) invoice_content_text.getText());
						edit.putString("title", String.valueOf(invoice_title_edit.getText()));
						edit.commit();
					
						//SQLiteDatabase database = helper.getWritableDatabase();
//						ContentValues values=new ContentValues();
//						values.put("title", String.valueOf(invoice_title_edit.getText()));
//						values.put("username", sp.getString("name", ""));
//						values.put("content", msgList.get(sp.getInt("position", 0)));
//						database.insert("invoice", null, values);
						
					}
					Toast.makeText(context, "保存成功", 0).show();
				} else {
					Toast.makeText(context, "请填写发票抬头", 0).show();
					return;
				}
				break;

		case R.id.invoice_layout:
			popWin = new PopupWindow(context);
			popWin.setWidth(invoice_layout.getWidth()); //设置宽度
			popWin.setHeight(130);	//设置popWin 高度
			initListview();
			popWin.setContentView(listView); //为popWindow填充内容
			popWin.setOutsideTouchable(true); // 点击popWin 以处的区域，自动关闭 popWin
			
			popWin.showAsDropDown(invoice_layout, 0, 0);
			
			break;
			}
		super.onClick(v);
		
	}

	private void initListview() {
		listView = new ListView(context);
		listView.setBackgroundResource(R.drawable.listview_background); //设置listView 背景
		listView.setDivider(null);	//设置条目之间的分隔线为null
		listView.setVerticalScrollBarEnabled(false); // 关闭
		listView.setAdapter(new MyListAdapter());
	}
	private class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return msgList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView == null){
				convertView = View.inflate(context, R.layout.list_item, null);
				holder = new ViewHolder();
				
				holder.delete = (ImageView) convertView.findViewById(R.id.delete);
				holder.tv_msg =(TextView) convertView.findViewById(R.id.tv_list_item);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.tv_msg.setText(msgList.get(position));
			
			holder.delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				//删除对应的条目
					msgList.remove(position);
					
					//刷新listView
					MyListAdapter.this.notifyDataSetChanged();
				}
			});
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//设置输入框 
					invoice_content_text.setText(msgList.get(position));
					Editor edit = sp.edit();
					edit.putInt("position", position);
					edit.commit();
					popWin.dismiss();
				}
			});
			
			return convertView;
		}
		}

	private class ViewHolder{
		TextView tv_msg;
		ImageView delete;
	}
	@Override
	public void init() {
		sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.invoice_activity, null);
		head_back_text = (TextView) findViewById(R.id.head_back_text);
		invoice_save_text = (TextView) findViewById(R.id.invoice_save_text);
		invoice_title_edit = (EditText) findViewById(R.id.invoice_title_edit);
		invoice_layout=(LinearLayout) findViewById(R.id.invoice_layout);
		invoice_content_text=(TextView) findViewById(R.id.invoice_content_text);
		
		//helper=new DBHelper(context);
		//sp=(Spinner) findViewById(R.id.invoice_content_spin);
		msgList = new ArrayList<String>();
		msgList.add("图书");
		msgList.add("音响");
		msgList.add("游戏");
		msgList.add("软件");
		msgList.add("资料");
		msgList.add("食品");
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.INVOICE;
	}

}
