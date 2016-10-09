package com.xiaoyu.shbookstore.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.Topic;
import com.xiaoyu.shbookstore.engine.TopicAndBrandEngine;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

/**
 * 促销快报界面
 *
 */
public class TopicView extends BaseView {
	private ListView promBulldtinLv;
	private MyAdapter adapter;
	private Topic topic;
	private List<Topic> topicInfoFromNet;
	private TextView backTv;
	private TextView textNull;
	private TopicAndBrandEngine engine;
	private Map<String,String> params;
	public TopicView(Context context) {
		super(context);
	}
	
	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.prom_bulletin_activity, null);
		promBulldtinLv = (ListView) findViewById(R.id.promBulldtinLv);
		backTv = (TextView) findViewById(R.id.backTv);
		textNull = (TextView) findViewById(R.id.textNull);
		engine = BeanFactory.getImpl(TopicAndBrandEngine.class);
		params = new HashMap<String,String>();
		params.put("page", "1");
		params.put("pageNum", "5");
		fillData();
	}
	
	/**
	 * 设置ListView子条目的点击事件
	 */
	@Override
	public void setListener() {
		promBulldtinLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				UiManager.getUiManager().changeView(TopicProductListView.class);
			}
		});		
		backTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UiManager.getUiManager().goBack();
			}
		});
	}

	private void fillData(){
		new MyHttpAsyncTask<Void>() {		
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if(topicInfoFromNet != null){					
					if(adapter == null){
						adapter = new MyAdapter();
						promBulldtinLv.setAdapter(adapter);
					}else{
						adapter.notifyDataSetChanged();
					}
				}else{
					promBulldtinLv.setVisibility(View.INVISIBLE);
					textNull.setVisibility(View.VISIBLE);
				}			
			}	
				
			@Override
			protected void onPreExecute() {				
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(Void... params1) {
				topicInfoFromNet = engine.getTopicInfoFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.TOPIC), params);
				System.out.println(topicInfoFromNet);
				return null;
			}		
		}.executeProxy();
	}
	
	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return topicInfoFromNet.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.prom_bulletin_item, null);
				holder.textContent = (TextView) convertView.findViewById(R.id.textContent);
				holder.imgIcon =  (SmartImageView) convertView.findViewById(R.id.imgIcon);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			topic = topicInfoFromNet.get(position);
			System.out.println("topicName----------"+topic.getName());
			holder.textContent.setText(topic.getName());
			holder.imgIcon.setImageUrl(topic.getPic());
//			try { 
//		        URL url = new URL(topic.getPic()); 
//		        holder.imgIcon.setImageBitmap(BitmapFactory.decodeStream(url.openStream())); 
//		    } catch (Exception e) { 
//		        		e.printStackTrace();
//		    } 
			return convertView;
		}
		
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		class ViewHolder{
			TextView textContent;
			//ImageView imgIcon;
			SmartImageView imgIcon;
		}
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.TOPICVIEW;
	}
}
