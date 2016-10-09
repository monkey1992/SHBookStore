package com.xiaoyu.shbookstore.engine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.domain.User;
import com.xiaoyu.shbookstore.domain.UserInfo;
import com.xiaoyu.shbookstore.engine.AccountEngine;
import com.xiaoyu.shbookstore.engine.BaseEngine;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class AccountEngineImpl extends BaseEngine implements AccountEngine {
	
	@Override
	public boolean login(User user) {
		if(user == null) {
			return false;
		}
		HttpClientUtil client = new HttpClientUtil();
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		//String json = client.sendPost(ConstantValue.COMMON_URI.concat(ConstantValue.LOGIN), params);
		String json = client.sendPost("http://10.0.2.2:8080/ECService/login", params);
//		String json = client.sendPost("http://192.168.0.101:8080/RedBaby/login", params);
		if(json != null && checkResponse(json)) {
			try {
				JSONObject jsonObj = new JSONObject(json);
				GlobalParams.userId = String.valueOf(
						JSON.parseObject(jsonObj.getString("userinfo"),
						UserInfo.class).getUserid()
						);
				client.headers[7] = new BasicHeader("userid", GlobalParams.userId);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean register(User user) {
		if(user == null) {
			return false;
		}
		HttpClientUtil client = new HttpClientUtil();
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		String json = client.sendPost(ConstantValue.COMMON_URI.concat(ConstantValue.REGISTER), params);
//		String json = client.sendPost("http://192.168.0.101:8080/RedBaby/register", params);	
		if(json != null && checkResponse(json)) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Product> getFavorite(String page,String pageNum) {
		HttpClientUtil client = new HttpClientUtil();
		Map<String,String> params = new HashMap<String,String>();
		params.put("page", page);
		params.put("pageNum", pageNum);
		String json = client.sendGet(ConstantValue.COMMON_URI.concat(ConstantValue.FAVORITE), params);
		List<Product> list = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			if(json != null && checkResponse(json)) {
				list = JSON.parseArray(jsonObject.getString("productlist"), Product.class);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public UserInfo getUserInfo() {
		UserInfo userInfo = null;
		HttpClientUtil client = new HttpClientUtil();
		System.out.println("getUserInfo:"+client.headers[7].getName()+","+client.headers[7].getValue());
		String json = client.sendGet(ConstantValue.COMMON_URI.concat(ConstantValue.USERINFO), null);
		if(json != null && checkResponse(json)) {
			try {
				JSONObject jsonObj = new JSONObject(json);
				userInfo = JSON.parseObject(jsonObj.getString("userinfo"),UserInfo.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return userInfo;
	}	
}
