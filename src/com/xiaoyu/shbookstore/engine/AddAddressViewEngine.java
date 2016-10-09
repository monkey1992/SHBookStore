package com.xiaoyu.shbookstore.engine;

import java.util.List;
import java.util.Map;

import com.xiaoyu.shbookstore.domain.AddressListInfo;

public interface AddAddressViewEngine {
	
	/**
	 * 添加地址信息
	 * @param uri 访问的路径
	 * @param params 访问需要的参数
	 * @return 
	 */
	List<AddressListInfo> addSuccessToNet(String uri, Map<String,String> params);
}

















