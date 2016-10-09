package com.xiaoyu.shbookstore.domain;
/**
 * 地址信息的封装
 */
public class AddressInfo 
{
   
   /**
    * 地址簿ID
    */
   private int id;
   
   /**
    * 收货人姓名
    */
   private String name;
   
   /**
    * 国家三级地址
    * 如："北京市海淀区"
    */
   private String address_area;
   
   /**
    * 地址详情 ："闵庄路3号"
    */
   private String address_detail;
   
   /**
    * @roseuid 534B81F103CE
    */
   public AddressInfo() 
   {
    
   }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAddress_area() {
	return address_area;
}

public void setAddress_area(String address_area) {
	this.address_area = address_area;
}

public String getAddress_detail() {
	return address_detail;
}

public void setAddress_detail(String address_detail) {
	this.address_detail = address_detail;
}
   
}
