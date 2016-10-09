package com.xiaoyu.shbookstore.domain;

import com.xiaoyu.shbookstore.dao.annotation.Column;
import com.xiaoyu.shbookstore.dao.annotation.ID;
import com.xiaoyu.shbookstore.dao.annotation.TableName;
import com.xiaoyu.shbookstore.db.DBHelper;

@TableName(DBHelper.TABLE_CART_NAME)
public class CartTable {

	@ID(autoincrement = true)
	@Column(DBHelper.TABLE_ID)
	private int id;

	@Column(DBHelper.TABLE_CART_PROD_ID)
	private String prod_id;

	@Column(DBHelper.TABLE_CART_PROD_NUM)
	private String prod_num;

	@Column(DBHelper.TABLE_CART_PROD_PROP)
	private String prod_prop;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_num() {
		return prod_num;
	}

	public void setProd_num(String prod_num) {
		this.prod_num = prod_num;
	}

	public String getProd_prop() {
		return prod_prop;
	}

	public void setProd_prop(String prod_prop) {
		this.prod_prop = prod_prop;
	}

}
