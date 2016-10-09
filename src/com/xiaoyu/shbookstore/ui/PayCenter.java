package com.xiaoyu.shbookstore.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.image.SmartImageView;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.dao.PayDao;
import com.xiaoyu.shbookstore.db.DBHelper;
import com.xiaoyu.shbookstore.domain.Cart;
import com.xiaoyu.shbookstore.domain.Order;
import com.xiaoyu.shbookstore.domain.PayMentBean;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class PayCenter extends BaseView {

	private TextView tback;
	private TextView torder;
	private TextView ordr_submit_bottom_text;
	private List<Product> prolist;
	private Bundle paybuddle;
	private MyAdapter adapter;
	private SharedPreferences sp;
	private RelativeLayout payment_address_rel;// 收货人
	private RelativeLayout payment_payType_rel;// zhifu方式
	private RelativeLayout payment_sendTime_rel;// 送货时间
	private RelativeLayout payment_invoice_rel;// 发票填写
	private RelativeLayout payment_remark_rel;// 留言信息
	private ListView payment_product_list;// 商品明细
	private PayMentBean bean;
	private TextView payment_address_text;
	private TextView payment_orderPrice_text;

	private TextView payment_payValue_text;
	private TextView payment_sendTimeValue_text;
	
	SQLiteDatabase database;
	DBHelper helper;
	

	private void initBeans() {
		//params1 = new HashMap<String, String>();
		//params1.put("sku", "1200001:3:1,2");
//		new MyHttpAsyncTask<Map>() {
//
//			@Override
//			protected String doInBackground(Map... params) {
//				HttpClientUtil httputil = new HttpClientUtil();
//				String sendPost = httputil.sendPost(ConstantValue.COMMON_URI
//						+ ConstantValue.CART, params[0]);
//				return sendPost;
//			}
//			protected void onPostExecute(String result) {
//				System.out.println(result);
//			};
//			
//		}.executeProxy();

		/*
		 * addr=engine.getAddressInfo(uri1, params1);
		 * prolist=engine.getProductList(uri1, params1);
		 * payment=engine.getPaymentInfo(uri1, params1);
		 * deli=engine.getDeliveryInfo(uri1, params1);
		 * proPerty=engine.getProductProperty(uri1, params1);
		 * cheup=engine.getCheckoutAddup(uri1, params1);
		 */
	}

	public PayCenter(Context context) {
		super(context);

	}

	@Override
	public void setListener() {
		tback.setOnClickListener(this);
		torder.setOnClickListener(this);
		payment_address_rel.setOnClickListener(this);
		payment_payType_rel.setOnClickListener(this);
		payment_sendTime_rel.setOnClickListener(this);
		payment_invoice_rel.setOnClickListener(this);
		payment_remark_rel.setOnClickListener(this);
		ordr_submit_bottom_text.setOnClickListener(this);
	}

	@Override
	public void init() {
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		PayDao dao=new PayDao(context);
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.payment_center_activity, null);
		payment_orderPrice_text=(TextView) findViewById(R.id.payment_orderPrice_text);
		payment_address_text=(TextView) findViewById(R.id.payment_address_text);
		tback = (TextView) findViewById(R.id.head_back_text);
		torder = (TextView) findViewById(R.id.ordr_submit_top_text);
		ordr_submit_bottom_text = (TextView) findViewById(R.id.ordr_submit_bottom_text);
		
		helper=new DBHelper(context);
		database = helper.getWritableDatabase();

//		uri1 = ConstantValue.COMMON_URI + ConstantValue.CHECK;
//		uri2 = ConstantValue.COMMON_URI + ConstantValue.OSUB;
//		uri3 = ConstantValue.COMMON_URI + ConstantValue.INVO;
		// bean=new PayMentBean();

		payment_address_rel = (RelativeLayout) findViewById(R.id.payment_address_rel);
		payment_payType_rel = (RelativeLayout) findViewById(R.id.payment_payType_rel);
		payment_sendTime_rel = (RelativeLayout) findViewById(R.id.payment_sendTime_rel);
		payment_invoice_rel = (RelativeLayout) findViewById(R.id.payment_invoice_rel);
		payment_remark_rel = (RelativeLayout) findViewById(R.id.payment_remark_rel);
		payment_product_list = (ListView) findViewById(R.id.payment_product_list);

		payment_payValue_text = (TextView) findViewById(R.id.payment_payValue_text);
		payment_sendTimeValue_text = (TextView) findViewById(R.id.payment_sendTimeValue_text);

//		params1 = new HashMap<String, String>();
//		params2 = new HashMap<String, String>();
//		engine = BeanFactory.getImpl(PaymentEngine.class);

		shopcar_total_buycount_text = (TextView) findViewById(R.id.shopcar_total_buycount_text);
		shopcar_total_bonus_text = (TextView) findViewById(R.id.shopcar_total_bonus_text);
		shopcar_total_money_text = (TextView) findViewById(R.id.shopcar_total_money_text);
		payment_remarkView_text=(TextView) findViewById(R.id.payment_remarkView_text);
		prolist = new ArrayList<Product>();
		//initlist();

		
		
	}

	private void initlist() {
		Product pro = new Product();
		pro.setPic(String.valueOf(R.drawable.book));
		pro.setName("小黄书");
		pro.setId(101);
		pro.setPrice("325.00");
		pro.setNumber(2);
		prolist.add(pro);
		Product pro1 = new Product();
		pro1.setPic(String.valueOf(R.drawable.book2));
		pro1.setName("恶魔法典");
		pro1.setId(102);
		pro1.setPrice("825.00");
		pro1.setNumber(1);
		prolist.add(pro1);
		Product pro2 = new Product();
		pro2.setPic(String.valueOf(R.drawable.ez));
		pro2.setName("三项之力");
		pro2.setId(103);
		pro2.setPrice("3850.00");
		pro2.setNumber(1);
		prolist.add(pro2);
		Product pro3 = new Product();
		pro3.setPic(String.valueOf(R.drawable.item));
		pro3.setName("无尽之刃");
		pro3.setId(104);
		pro3.setPrice("3880.00");
		pro3.setNumber(1);
		prolist.add(pro3);
	}


	@Override
	public void onResume() {
		//initBeans();
		 prolist = Cart.getCart().getProduct();
		if(bundle!=null){
			payment_address_text.setText("姓名："+bundle.getString("name")+"\r\n"+"电话："+bundle.getString("phonenumber")+"\r\n"+"收货地址："+bundle.getString("areadetail"));
			Editor edit = sp.edit();
			edit.putString("username", bundle.getString("name"));
			edit.commit();
		}

		String str = sp.getString("paytype", "");
		String str1 = sp.getString("sendtime", "");
		payment_payValue_text.setText(str);
		GlobalParams.ispayvalue = true;
		if (str.equals("")) {
			GlobalParams.ispayvalue = false;
		}
		GlobalParams.issendtime = true;
		payment_sendTimeValue_text.setText(str1);
		if (str1.equals("")) {
			GlobalParams.issendtime = false;
		}
		adapter = new MyAdapter();
		payment_product_list.setAdapter(adapter);
		changeNotice();
	}
private String format;
	private void setdb() {
		
		ContentValues values=new ContentValues();
		values.put("orderid", format);
		values.put("total", num);
		values.put("username", bundle.getString("name"));
		values.put("usercall", bundle.getString("phonenumber"));
		values.put("place", bundle.getString("areadetail"));
		values.put("paytype",(String) payment_payValue_text.getText());
		values.put("sendtime", (String) payment_sendTimeValue_text.getText());
		values.put("msg", String.valueOf(payment_remarkView_text.getText()));
		database.insert("payment1", null, values);
//		
//		for(int x=0;x<prolist.size();x++){
//			ContentValues values1=new ContentValues();
//			values.put("proname", prolist.get(x).getName());
//			values.put("propic", prolist.get(x).getPic());
//			values.put("proid", prolist.get(x).getId());
//			values.put("price", prolist.get(x).getPrice());
//			values.put("count", prolist.get(x).getNumber());
//			values.put("totalprice", Float.parseFloat(prolist.get(x).getPrice())* prolist.get(x).getNumber());
//			database.insert("product", null, values1);
//		}
	}

	private PopupWindow popWin;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_back_text:
			UiManager.getUiManager().goBack();
			break;
		case R.id.ordr_submit_top_text:
		case R.id.ordr_submit_bottom_text:
			if (prolist.size() > 0 && GlobalParams.ispayvalue
					&& GlobalParams.issendtime&&bundle!=null&&num!=0) {
				//prolist.clear();
				//payment_payValue_text.setText("");
				//payment_sendTimeValue_text.setText("");
				payment_address_text.setText("");
				
				sendpost();
				setdb();
				bundle=null;
			} else {
				Toast.makeText(context, "请先确定送货时间、地址和商品", 0).show();
				return;
			}
			break;
		case R.id.payment_address_rel:
			UiManager.getUiManager().changeView(AddressListView.class);
			break;
		case R.id.payment_payType_rel:
			UiManager.getUiManager().changeView(PayTypeView.class);
			break;
		case R.id.payment_sendTime_rel:
			UiManager.getUiManager().changeView(SentTimeView.class);
			break;
		case R.id.payment_invoice_rel:
			UiManager.getUiManager().changeView(InVoiceView.class);
			break;
		case R.id.payment_remark_rel:
			AlertDialog.Builder al=new Builder(context);
			dialog=al.create();
			View view=View.inflate(context, R.layout.payment_center_remark, null);
			ok = (Button) view.findViewById(R.id.ok);
			cancel = (Button) view.findViewById(R.id.cancel);
			dialog_remark_edit=(EditText) view.findViewById(R.id.dialog_remark_edit);
			cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Editable str = dialog_remark_edit.getText();
					if(!str.equals("")){
						payment_remarkView_text.setText(str);
						dialog.dismiss();
					}
				}
			});
			dialog.setView(view, 0, 0, 0, 0);
			dialog.show();
			
			break;
		}
		super.onClick(v);
	}
	private void sendpost() {
		Map<String,String> params=new HashMap<String, String>();
		String value=null;
			for(Product pro:prolist){
				value=pro.getId()+":"+pro.getProdAccount()+":"+"1,2";
			}
		params.put("sku", value);
		params.put("addressid",bundle.getString("areaid"));
		params.put("paymentid",String.valueOf(GlobalParams.paymentid));
		params.put("deliveryid",String.valueOf(GlobalParams.deliveryid));
		params.put("invoicetype",String.valueOf(GlobalParams.invoicetype));
		params.put("invoicetitle",sp.getString("title", ""));
		params.put("invoicecontent",sp.getString("content", ""));
		new MyHttpAsyncTask<Map>() {
			protected void onPostExecute(String result) {
				Order order=null;
				try {
					JSONObject obj=new JSONObject(result);
					order=JSON.parseObject(obj.getString("orderinfo"), Order.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(order!=null){
					paybuddle.putString("orderid", order.getOrderid());
					UiManager.getUiManager().changeView(SubmitSuccess.class,
							paybuddle);
				}else {
					Toast.makeText(context, "服务器忙。。。", 0).show();
					return;
				}
			};
			
			@Override
			protected String doInBackground(Map... params) {
				HttpClientUtil util=new HttpClientUtil();
				String uri=ConstantValue.COMMON_URI+ConstantValue.OSUB;
				return util.sendPost(uri, params[0]);
			}
		}.executeProxy(params);
		
	}
	private TextView payment_remarkView_text;
	private EditText dialog_remark_edit;
	private AlertDialog dialog;
	private Button ok;
	private Button cancel;
	
	private class ViewHolder {
		private SmartImageView shopcar_item_prodImage_img;
		private TextView shopcar_item_prodName_text;
		private TextView shopcar_item_prodId_text;
		private TextView shopcar_item_prodPrice_text;
		private TextView shopcar_item_prodCount_text;
		private TextView shopcar_item_subtotal_text;
		private TextView shopcar_item_delete_text;
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return prolist.size();
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			if (convertView == null) {
				convertView = View.inflate(context,
						R.layout.payment_center_items, null);
				holder.shopcar_item_prodImage_img = (SmartImageView) convertView.findViewById(R.id.shopcar_item_prodImage_img);
				holder.shopcar_item_prodCount_text = (TextView) convertView
						.findViewById(R.id.shopcar_item_prodCount_text);
				holder.shopcar_item_prodId_text = (TextView) convertView
						.findViewById(R.id.shopcar_item_prodId_text);
				holder.shopcar_item_prodName_text = (TextView) convertView
						.findViewById(R.id.shopcar_item_prodName_text);
				holder.shopcar_item_prodPrice_text = (TextView) convertView
						.findViewById(R.id.shopcar_item_prodPrice_text);
				holder.shopcar_item_subtotal_text = (TextView) convertView
						.findViewById(R.id.shopcar_item_subtotal_text);
				holder.shopcar_item_delete_text = (TextView) convertView
						.findViewById(R.id.shopcar_item_delete_text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// holder.shopcar_item_prodImage_img.setImageURI(Uri.parse(bean.getProlist().get(position).getPic()));//商品图片
			holder.shopcar_item_prodImage_img.setImageUrl(prolist.get(position).getPic());

			// holder.shopcar_item_prodCount_text.setText(bean.getProlist().get(position).getNumber());//商品个数
			holder.shopcar_item_prodCount_text.setText(String.valueOf(prolist
					.get(position).getProdAccount()));// 商品个数
			System.out.println(prolist.get(position).getProdAccount());
			// holder.shopcar_item_prodId_text.setText(bean.getProlist().get(position).getId());//商品id
			holder.shopcar_item_prodId_text.setText(String.valueOf(prolist.get(
					position).getId()));// 商品id
			// holder.shopcar_item_prodName_text.setText(bean.getProlist().get(position).getName());//商品名
			holder.shopcar_item_prodName_text.setText(prolist.get(position)
					.getName());// 商品名
			// holder.shopcar_item_prodPrice_text.setText(bean.getProlist().get(position).getPrice());//商品价格
			holder.shopcar_item_prodPrice_text.setText("￥"
					+ prolist.get(position).getPrice());// 商品价格
			holder.shopcar_item_subtotal_text.setText(String.valueOf(Float
					.parseFloat(prolist.get(position).getPrice())
					* Integer.parseInt(prolist.get(position).getProdAccount())));// 商品小计
			holder.shopcar_item_delete_text.setVisibility(View.VISIBLE);
			holder.shopcar_item_delete_text
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							prolist.remove(position);
							changeNotice();
							notifyDataSetChanged();
						}

					});
			
			return convertView;
		}

	}

	private TextView shopcar_total_buycount_text;
	private TextView shopcar_total_bonus_text;
	private TextView shopcar_total_money_text;

	// private CheckoutAddup addup;
	Float num = 0.0f;
	private void changeNotice() {
		// addup=new CheckoutAddup();
		int count = 0;
		num=0.0f;
		for (Product pro : prolist) {
			count +=Integer.parseInt(pro.getProdAccount());
			num += (Float.parseFloat(pro.getPrice()) * Integer.parseInt(pro.getProdAccount()));
		}

		// addup.setTotal_count(count);
		// addup.setTotal_price(num);
		// addup.setFreight(200);
		shopcar_total_buycount_text.setText(String.valueOf(count));
		shopcar_total_bonus_text.setText(String.valueOf(200));
		shopcar_total_money_text.setText("￥" + String.valueOf(num));
		payment_orderPrice_text.setText("您的订单总金额是："+"￥" + String.valueOf(num));
		paybuddle = new Bundle();
		DecimalFormat decimalFormat = new DecimalFormat("000000000");
		Random random=new Random();
		format = decimalFormat.format(random.nextInt(999999999)+1);
		paybuddle.putString("orderid", format);
		// bundle.putString("price", String.valueOf(order.getPrice()));
		paybuddle.putString("price", "￥" + String.valueOf(num));
		// bundle.putString("paymenttype", order.get);
		paybuddle.putString("paymenttype",sp.getString("paytype", ""));
		paybuddle.putInt("count", count);
	}

	
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return ConstantValue.PAYCENTER;
	}
}
