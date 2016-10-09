package com.xiaoyu.shbookstore.domain;

import java.util.List;

/**
 * 推荐品牌访问网络后返回的bean
 * @author cjx
 *
 */
public class Brand {
	private String key;
	private List<BrandInfo> value;
	@Override
	public String toString() {
		return "Brand [key=" + key + ", value=" + value + "]";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<BrandInfo> getValue() {
		return value;
	}
	public void setValue(List<BrandInfo> value) {
		this.value = value;
	}
	
	
}

