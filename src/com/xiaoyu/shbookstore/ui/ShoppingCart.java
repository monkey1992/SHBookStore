package com.xiaoyu.shbookstore.ui;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.image.SmartImageView;
import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.customctrl.MyGridView;
import com.xiaoyu.shbookstore.domain.Cart;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.protocol.CartProtocal;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class ShoppingCart extends BaseView {

	public ShoppingCart(Context context) {
		super(context);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context,
				R.layout.shopping_car_activity, null);// 添加购物车布局
		uiManager = UiManager.getUiManager();// 初始化UI管理器

		cart2 = Cart.getCart();

		product = cart2.getProduct();

		initID();

		isBlank();
	}

	@Override
	public void onResume() {
		isBlank();
		initTotal();
		BottomInit();
		super.onResume();
	}

	@Override
	public void onPause() {
		// uiManager.clearOperations();
		super.onPause();
	}

	/**
	 * 异步获取网络数据
	 */
	private void getData() {
		new MyHttpAsyncTask<Map>() {
			@Override
			protected String doInBackground(Map... params) {
				HttpClientUtil httputil = new HttpClientUtil();
				String sendPost = httputil.sendPost(ConstantValue.COMMON_URI
						+ ConstantValue.CART, params[0]);
				return sendPost;
			}

			@Override
			protected void onPostExecute(String result) {
				if (result != null) {
					cart = JSON.parseObject(result, CartProtocal.class);
				}
				super.onPostExecute(result);
			}

		}.executeProxy(params);
	}

	/**
	 * 购物车是否为空product.size()
	 */
	private void isBlank() {
		if (product != null && product.size() > 0) {
//		if(true){
			shopcar_update_text.setVisibility(shopcar_update_text.VISIBLE);
			shopcar_toPay_text.setVisibility(shopcar_toPay_text.VISIBLE);
			shopcar_body_srcoll.setVisibility(shopcar_body_srcoll.VISIBLE);
			shopcar_default_img.setVisibility(shopcar_default_img.GONE);
			shopcar_toBuy_text.setVisibility(shopcar_toBuy_text.GONE);
			shopcar_null_text.setVisibility(shopcar_null_text.GONE);

			adapter = new MyAdapter();
			shopcar_product_list.setAdapter(adapter);
			lv_prom_list.setAdapter(new MyAdapterProm());

		} else {
			shopcar_update_text.setVisibility(shopcar_update_text.GONE);
			shopcar_toPay_text.setVisibility(shopcar_toPay_text.GONE);
			shopcar_body_srcoll.setVisibility(shopcar_body_srcoll.GONE);
			shopcar_default_img.setVisibility(shopcar_default_img.VISIBLE);
			shopcar_toBuy_text.setVisibility(shopcar_toBuy_text.VISIBLE);
			shopcar_null_text.setVisibility(shopcar_null_text.VISIBLE);

			// textShopCarNum.setVisibility(textShopCarNum.GONE);
		}
	}

	/**
	 * 自定义商品条目适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends BaseAdapter {
//		private ArrayList<Product> product2;
//		public MyAdapter() {
//			product2 = new ArrayList<Product>();
//			Product pro = new Product();
//			int oneProd = 0;//第一个数量
//			int countProd = 0;//第二个开始累加的数量
//			int oneTotal = 0;//第一个小计
//			int countTotal = 0;//第二个开始累加的小计
//			for(int x=0 ; x<product.size()-1;x++){
//				for(int y=1;y<product.size();y++){
//					if(product.get(x).getName().equals(product.get(y).getName())){
//						oneProd = Integer.parseInt(product.get(x).getProdAccount());
//						int two = Integer.parseInt(product.get(y).getProdAccount());
//						countProd += two;
//						
//						oneTotal = Integer.parseInt(product.get(x).getTotalPrice());
//						int three = Integer.parseInt(product.get(y).getTotalPrice());
//						countTotal += three;
//					}
//				}
//				if(!product2.contains(product.get(x).getName())){
//					pro.setProdAccount(String.valueOf(oneProd+countProd));
//					pro.setTotalPrice(String.valueOf(countTotal+oneTotal));
//					pro.setPic(product.get(x).getPic());
//					pro.setName(product.get(x).getName());
//					pro.setPro_id(product.get(x).getPro_id());
//					pro.setPrice(product.get(x).getPrice());
//					product2.add(pro);
//				}
//				oneProd = 0;
//				countProd = 0;
//				oneTotal = 0;
//				countTotal = 0;
//			}
//		}
		
		@Override
		public int getCount() {
			return product.size();
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
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder;

			if (convertView != null && convertView instanceof RelativeLayout) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(context, R.layout.shopping_car_listitem,
						null);
				holder = new ViewHolder();

				holder.prodImage_img = (SmartImageView) view
						.findViewById(R.id.shopcar_item_prodImage_img);
				holder.prodName_text = (TextView) view
						.findViewById(R.id.shopcar_item_prodName_text);
				holder.prodId_text = (TextView) view
						.findViewById(R.id.shopcar_item_prodId_text);
				holder.prodPrice_text = (TextView) view
						.findViewById(R.id.shopcar_item_prodPrice_text);
				holder.prodCount_text = (TextView) view
						.findViewById(R.id.shopcar_item_prodCount_text);
				holder.prodCount_edit = (EditText) view
						.findViewById(R.id.shopcar_item_prodCount_edit);
				holder.subtotal_text = (TextView) view
						.findViewById(R.id.shopcar_item_subtotal_text);
				holder.delete_text = (TextView) view
						.findViewById(R.id.shopcar_item_delete_text);
				delete_text = (TextView) view
						.findViewById(R.id.shopcar_item_delete_text);
				view.setTag(holder);
			}
			if (!product.isEmpty()) {
				Product pro = product.get(position);
				holder.prodImage_img.setImageUrl(pro.getPic());
				holder.prodName_text.setText(pro.getName());
				holder.prodId_text.setText(String.valueOf(pro.getPro_id()));
				holder.prodPrice_text.setText(pro.getPrice());
				holder.prodCount_text
						.setVisibility(holder.prodCount_text.VISIBLE);
				if (pro.getProdAccount() != null) {
					holder.prodCount_text.setText(pro.getProdAccount());
				} else {
					holder.prodCount_text.setText("数量获取失败");
				}
				holder.subtotal_text.setText(pro.getTotalPrice());
			}
			return view;
		}
	}

	/**
	 * 优惠清单
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapterProm extends BaseAdapter {
		@Override
		public int getCount() {
			return 2;
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
			View view;
			ViewHolder holder;

			if (convertView != null && convertView instanceof RelativeLayout) {
				view = convertView;
			} else {
				view = View.inflate(context, R.layout.shopping_car_listitem_2,
						null);
				PromList = (TextView) view
						.findViewById(R.id.shopcar_total_bonus_text_2);
			}
			String string = prom[position];
			PromList.setText(string);
			return view;
		}
	}

	/**
	 * 监听设置
	 */
	@Override
	public void setListener() {
		// 去结算
		shopcar_toPay_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				uiManager.changeView(PayCenter.class);
			}
		});
		// 清空
		shopcar_update_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				product.removeAll(product);
				isBlank();
			}
		});
		// 去逛逛按钮监听
		shopcar_toBuy_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				uiManager.changeView(HomeView.class);
			}
		});
		// 结算按钮
		shopcar_bottom_toPay_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uiManager.changeView(PayCenter.class);
			}
		});

		shopcar_product_list
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						if (!product.isEmpty()) {
							product.remove(arg2);
						}
						isBlank();
						
						initTotal();

						adapter.notifyDataSetChanged();
						return false;
					}
				});
	}

	@Override
	public int getIdentifier() {
		return ConstantValue.VIEW_SHOPPING_CART;
	}

	/**
	 * 适配器复用对象内部类
	 * 
	 * @author Administrator
	 */
	static class ViewHolder {
		/**
		 * 商品图片
		 */
		public SmartImageView prodImage_img;
		/**
		 * 商品名
		 */
		public TextView prodName_text;
		/**
		 * 编码
		 */
		public TextView prodId_text;
		/**
		 * 单价
		 */
		public TextView prodPrice_text;
		/**
		 * 数量
		 */
		public TextView prodCount_text;
		/**
		 * 修改数量-默认关闭
		 */
		public EditText prodCount_edit;
		/**
		 * 小计
		 */
		public TextView subtotal_text;
		/**
		 * 删除商品
		 */
		public TextView delete_text;
	}

	/**
	 * 初始化资源ID
	 */
	private void initID() {

		shopcar_update_text = (TextView) findViewById(R.id.shopcar_update_text);
		shopcar_toPay_text = (TextView) findViewById(R.id.shopcar_toPay_text);

		shopcar_body_srcoll = (ScrollView) findViewById(R.id.shopcar_body_srcoll);
		shopcar_total_buycount_text_1 = (TextView) findViewById(R.id.shopcar_total_buycount_text_1);
		shopcar_total_bonus_text_1 = (TextView) findViewById(R.id.shopcar_total_bonus_text_1);
		shopcar_total_money_text_1 = (TextView) findViewById(R.id.shopcar_total_money_text_1);

		shopcar_bottom_toPay_text = (TextView) findViewById(R.id.shopcar_bottom_toPay_text);

		lv_prom_list = (ListView) findViewById(R.id.lv_prom_list);
		shopcar_product_list = (MyGridView) findViewById(R.id.shopcar_product_list);

		shopcar_default_img = (ImageView) findViewById(R.id.shopcar_default_img);
		shopcar_toBuy_text = (TextView) findViewById(R.id.shopcar_toBuy_text);
		shopcar_null_text = (TextView) findViewById(R.id.shopcar_null_text);

		textShopCarNum = (TextView) findViewById(R.id.textShopCarNum);
		imgShoppingCar = (ImageView) findViewById(R.id.imgShoppingCar);
		home = (ImageView) findViewById(R.id.imgHome);
		search = (ImageView) findViewById(R.id.imgSearch);
		classify = (ImageView) findViewById(R.id.imgClassify);
		more = (ImageView) findViewById(R.id.imgMore);

	}

	/**
	 * 底部导航兰变色
	 */
	private void BottomInit() {
		// home.setImageResource(R.drawable.bar_home_normal);
		// search.setImageResource(R.drawable.bar_search_normal);
		// classify.setImageResource(R.drawable.bar_class_normal);
		// more.setImageResource(R.drawable.bar_more_normal);
		// imgShoppingCar.setImageResource(R.drawable.bar_shopping_selected);
	}

	/**
	 * 初始化头信息
	 */
	private void initTotal() {
		int allProice = 0;
		int allcount = 0;
		int allBonus = 0;
		if (product != null && product.size() > 0) {
			for (Product pro : product) {
				allProice += Integer.parseInt(pro.getTotalPrice());
				allcount += Integer.parseInt(pro.getProdAccount());
			}
			allBonus = (allcount * 10 + allProice) / 13;
			shopcar_total_buycount_text_1.setText(String.valueOf(allcount));
			shopcar_total_bonus_text_1.setText(String.valueOf(allBonus));
			shopcar_total_money_text_1.setText(String.valueOf(allProice));
		}
	}

	/**
	 * 清除按钮
	 */
	private TextView shopcar_update_text;
	/**
	 * 去结算
	 */
	private TextView shopcar_toPay_text;
	/**
	 * 中间滚动
	 */
	private ScrollView shopcar_body_srcoll;
	/**
	 * 数量总计
	 */
	private TextView shopcar_total_buycount_text_1;
	/**
	 * 赠送积分总计
	 */
	private TextView shopcar_total_bonus_text_1;
	/**
	 * 金额总计
	 */
	private TextView shopcar_total_money_text_1;
	/**
	 * 优惠信息
	 */
	private TextView PromList;
	/**
	 * 去结算
	 */
	private TextView shopcar_bottom_toPay_text;
	/**
	 * 底部导航
	 */
	private ImageView imgShoppingCar;

	/**
	 * 商品列表
	 */
	private MyGridView shopcar_product_list;
	/**
	 * 优惠列表
	 */
	private ListView lv_prom_list;

	/**
	 * 没有商品购物车图片
	 */
	private ImageView shopcar_default_img;
	/**
	 * 去逛逛
	 */
	private TextView shopcar_toBuy_text;
	/**
	 * 没有商品提醒
	 */
	private TextView shopcar_null_text;
	/**
	 * 发送消息用map
	 */
	private Map<String, String> params;
	/**
	 * 购物车协议bean
	 */
	private CartProtocal cart;
	/**
	 * 购物车小球
	 */
	private TextView textShopCarNum;
	/**
	 * UI管理器
	 */
	UiManager uiManager;

	protected static final String TAG = "ShoppingCart";
	/**
	 * 导航--主页面
	 */
	private ImageView home;
	/**
	 * 导航--分类
	 */
	private ImageView search;
	/**
	 * 导航--搜索
	 */
	private ImageView classify;
	/**
	 * 导航--更多
	 */
	private ImageView more;
	/**
	 * 商品列表
	 */
	private MyAdapter adapter;
	/**
	 * 商品条目删除
	 */
	private TextView delete_text;
	private Cart cart2;
	private ArrayList<Product> product;
	private String[] prom = { "今日吐血大甩卖全场最高可享8折", "总价超过1元即可全球包邮，仅限本日" };
	private int allProice;
	private int allcount;
	private int allBonus;

}
