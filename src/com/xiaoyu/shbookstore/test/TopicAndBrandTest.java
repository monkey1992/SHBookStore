package com.xiaoyu.shbookstore.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.test.AndroidTestCase;
import android.util.Log;

import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.domain.Topic;
import com.xiaoyu.shbookstore.engine.impl.TopicAndBrandEngineImpl;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class TopicAndBrandTest extends AndroidTestCase {
	private static final String TAG = "TopicAndBrandTest";

	public void testServiceTopicList(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("page", "1");
		params.put("pageNum", "3");
		TopicAndBrandEngineImpl engineImpl = new TopicAndBrandEngineImpl();
		List<Topic> topicInfoList = engineImpl.getTopicInfoFromNet(ConstantValue.COMMON_URI.concat(ConstantValue.TOPIC), params);
		for(Topic item : topicInfoList){
				Log.i(TAG, item.toString());
		}
	}
	public void testpayMent(){
		Map<String,String> params = new HashMap<String,String>();
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		params.put("sku", "1200001:3:1,2|1200004:2:2,3");
		String str = httpClientUtil.sendPost("http://192.168.1.26/ECService/checkout", params);
		//String str=httpClientUtil.sendGet("http://192.168.1.26/ECService/invoice", null);
		System.out.println(str);
		//PaymentengineImpl engine=new PaymentengineImpl();
//		String str=engine.getServiceString("http://192.168.1.26/ECService/checkout", params);
//		System.err.println(str);
//		PayMentBean bean = engine.getPayMentBean("http://192.168.1.26/ECService/checkout", params);
//		System.err.println(bean);
	}
}
