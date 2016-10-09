package com.xiaoyu.shbookstore.engine.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.domain.Brand;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.domain.Topic;
import com.xiaoyu.shbookstore.engine.BaseEngine;
import com.xiaoyu.shbookstore.engine.TopicAndBrandEngine;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

/**
 * 专题及品牌访问服务器业务实现类
 *
 */
public class TopicAndBrandEngineImpl extends BaseEngine implements TopicAndBrandEngine {
	/**
	 * 促销快报访问服务器获取促销信息的方法
	 */
	@Override
	public List<Topic> getTopicInfoFromNet(String uri, Map<String,String> params) {
		List<Topic> result;	
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String topicInfoJson = httpClientUtil.sendGet(uri, params);	
		JSONObject jsonObject;
		try {
			//将服务器返回来的json字符串数据转换成JSONObject类型，以便作为checkResponse的参数
			jsonObject = new JSONObject(topicInfoJson);
			//检查服务器返回来的"response"字段对应的值是否为"error",如果不为"error"则返回true，才能进行接下来的操作
			if(checkResponse(jsonObject)){
				//需要用fastjson这个jar包中的工具类JSON将jsonObject解析出来，然后封装到List<Topic>这个集合中						
				String topicList = jsonObject.getString("topic");//topicList的格式为:[{...},{...},{...}......]
				System.out.println("topiclist:"+topicList);
				result = JSON.parseArray(topicList, Topic.class);
				System.out.println("result-----------------"+result);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Brand> getBrandInfosFromNet(String uri,
			Map<String, String> params) {
		List<Brand> result;	
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String brandJson = httpClientUtil.sendGet(uri, params);		
		JSONObject jsonObject;
		try {
			//将服务器返回来的json字符串数据转换成JSONObject类型，以便作为checkResponse的参数
			jsonObject = new JSONObject(brandJson);
			//检查服务器返回来的"response"字段对应的值是否为"error",如果不为"error"则返回true，才能进行接下来的操作
			if(checkResponse(jsonObject)){
				//需要用fastjson这个jar包中的工具类JSON将jsonObject解析出来，然后封装到List<Topic>这个集合中			
				String brandInfos = jsonObject.getString("brand");//topicList的格式为:[{...},{...},{...}......]
				result = JSON.parseArray(brandInfos, Brand.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<Product> getProductListFromNet(String uri,
			Map<String, String> params){
		List<Product> result;	
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String productJson = httpClientUtil.sendGet(uri, params);	
		JSONObject jsonObject;
		try {
			//将服务器返回来的json字符串数据转换成JSONObject类型，以便作为checkResponse的参数
			jsonObject = new JSONObject(productJson);
			//检查服务器返回来的"response"字段对应的值是否为"error",如果不为"error"则返回true，才能进行接下来的操作
			if(checkResponse(jsonObject)){
				//需要用fastjson这个jar包中的工具类JSON将jsonObject解析出来，然后封装到List<Topic>这个集合中
				String productList = jsonObject.getString("productlist");//topicList的格式为:[{...},{...},{...}......]
				result = JSON.parseArray(productList, Product.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 专题商品列表访问服务器获取专题商品列表信息的方法
	 */
	@Override
	public List<Product> getTopicProductListFromNet(String uri,
			Map<String, String> params) {
		return getProductListFromNet(uri,params);
	}

	/**
	 * 品牌商品列表访问服务器获取品牌商品列表信息的方法
	 */
	@Override
	public List<Product> getBrandProductListFromNet(String uri,
			Map<String, String> params) {
		return getProductListFromNet(uri,params);
	}
	
	/**
	 * 限时抢购访问服务器获取商品列表信息的方法
	 */
	@Override
	public List<Product> getLimitBuyProductListFromNet(String uri,
			Map<String, String> params) {
		return getProductListFromNet(uri,params);
	}
	
	/**
	 * 新品上架访问服务器获取商品列表信息的方法
	 */
	@Override
	public List<Product> getNewProductListFromNet(String uri,
			Map<String, String> params) {
		return getProductListFromNet(uri,params);
	}
	
	/**
	 * 热门单品访问服务器获取商品列表信息的方法
	 */
	@Override
	public List<Product> getHotProductListFromNet(String uri,
			Map<String, String> params) {
		return getProductListFromNet(uri,params);
	}
}
