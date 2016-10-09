package com.xiaoyu.shbookstore.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.message.BasicHeader;

import android.test.AndroidTestCase;

import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class OrderTest extends AndroidTestCase{
	
	
	public void testOrder() {
		HttpClientUtil client = new HttpClientUtil();
		client.headers[7] = new BasicHeader("userId", "2537a732-dccf-49c5-b562-82fe8095b2e3");
		Map<String,String> params = new HashMap<String, String>();
		params.put("sku", "1200001:3:1,2");
		params.put("addressid", "3357b3ac-4045-4543-a3f9-c0e4298606fe");
		params.put("paymentid", "1");
		params.put("deliveryid", "1");
		params.put("invoicetype", "1");
		params.put("invoicetitle", "北京红孩子");
		params.put("invoicecontent", "1");
		String post = client.sendPost(ConstantValue.COMMON_URI.concat(ConstantValue.OSUB), params);
		System.out.println(post);
	}
	
}
