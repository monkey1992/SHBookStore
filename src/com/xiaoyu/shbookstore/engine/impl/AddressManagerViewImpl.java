package com.xiaoyu.shbookstore.engine.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.domain.AddressListInfo;
import com.xiaoyu.shbookstore.engine.AddressManagerViewEngine;
import com.xiaoyu.shbookstore.engine.BaseEngine;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public class AddressManagerViewImpl extends BaseEngine implements AddressManagerViewEngine {
	
	
	@Override
	public List<AddressListInfo> getAddrListInfoFromNet(String uri,
			Map<String, String> params) {
		
		List<AddressListInfo> result;//存放结果数据
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String addrListJSON = httpClientUtil.sendGet(uri, params);
		JSONObject jsonObject;
		try {
			//将服务器返回来的json字符串数据转换成JSONObject类型，以便作为checkResponse的参数
			jsonObject = new JSONObject(addrListJSON);
			//检查服务器返回来的"response"字段对应的值是否为"error",如果不为"error"则返回true，才能进行接下来的操作
			if(checkResponse(jsonObject)){
				//需要用fastjson这个jar包中的工具类JSON将jsonObject解析出来，然后封装到List<AddressListInfo>这个集合中
				
				String addrListInfo = jsonObject.getString("addresslist");
				result = JSON.parseArray(addrListInfo, AddressListInfo.class);
				return result;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
