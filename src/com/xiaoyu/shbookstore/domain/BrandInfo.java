package com.xiaoyu.shbookstore.domain;
/**
 * 推荐品牌访问网络后返回的bean中的品牌信息bean
 * @author cjx
 *
 */
public class BrandInfo {
	private String id;
	private String name;
	private String pic;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "BrandInfo [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}
	
	
}
