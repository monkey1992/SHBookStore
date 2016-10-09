package com.xiaoyu.shbookstore.domain;

public class Category {
	private int id;
	private int parentId;
	private String isLeafNode;
	private String name;
	private String pic;
	private String tag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getIsLeafNode() {
		return isLeafNode;
	}
	public void setIsLeafNode(String isLeafNode) {
		this.isLeafNode = isLeafNode;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId
				+ ", isLeafNode=" + isLeafNode + ", name=" + name + ", pic="
				+ pic + ", tag=" + tag + "]";
	}
	
	
}
