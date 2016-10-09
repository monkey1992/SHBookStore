package com.xiaoyu.shbookstore.domain;

import java.io.Serializable;

//删改查：需要封装的信息是： userid name phonenumber areadetail areaid
public class AddressListInfo implements Serializable{
	//用户id
	private String userid;
	//用户姓名
	private String name;
	//电话号码
	private String phonenumber;
	//详细地址
	private String areadetail;
	//地址id
	private String areaid;
	
	private String address_id;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAreadetail() {
		return areadetail;
	}
	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	
}





















