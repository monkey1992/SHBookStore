package com.xiaoyu.shbookstore.engine.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.domain.AddressListInfo;
import com.xiaoyu.shbookstore.engine.AddAddressViewEngine;
import com.xiaoyu.shbookstore.engine.BaseEngine;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class AddAddressViewEngineImpl extends BaseEngine implements AddAddressViewEngine {

	@Override
	public List<AddressListInfo> addSuccessToNet(String uri,
			Map<String, String> params) {
		List<AddressListInfo> result;
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		
		String addInfo = httpClientUtil.sendPost(uri, params);//服务器返回的json字符串
		JSONObject jsonObject ;
		try {
			jsonObject= new JSONObject(addInfo);
			if(checkResponse(jsonObject)){
				String addresslist = jsonObject.getString("addresslist");
				result=JSON.parseArray(addresslist, AddressListInfo.class);
				return result;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
