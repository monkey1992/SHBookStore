package com.xiaoyu.shbookstore.engine;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.MyError;
import com.xiaoyu.shbookstore.util.HttpClientUtil;

public abstract class BaseEngine {
	
	/**
	 * 检查服务器是否成功更新数据
	 * @param object 包装了json格式的数据对象
	 * @return 是否成功
	 */
	public boolean checkResponse(JSONObject object) {
		try {
			String response = object.getString("response");
			if("error".equals(response)) {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getServiceString(String uri, Map<String, String> params){
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String str = httpClientUtil.sendPost(uri, params);
		return str;
	}

	public boolean checkResponse(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			String response = jsonObject.getString("response");
			if("error".equals(response)) {
				GlobalParams.myError = JSON.parseObject(jsonObject.getString("error"),MyError.class);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

