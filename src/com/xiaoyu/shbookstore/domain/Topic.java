package com.xiaoyu.shbookstore.domain;
/**
 * 促销快报访问服务器后返回的bean
 * @author cjx
 *
 */
public class Topic {
	private String id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String pic;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}
	
	
	
	
}
