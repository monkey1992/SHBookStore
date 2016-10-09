package com.xiaoyu.shbookstore.domain;

import com.xiaoyu.shbookstore.dao.annotation.Column;
import com.xiaoyu.shbookstore.dao.annotation.ID;
import com.xiaoyu.shbookstore.dao.annotation.TableName;
import com.xiaoyu.shbookstore.db.DBHelper;


@TableName(DBHelper.TABLE_USER_NAME)
public class User {
	@ID(autoincrement=true)
	@Column(DBHelper.TABLE_ID)
	private int _id;
	
	@Column(DBHelper.TABLE_USER_USERNAME)
	private String username;
	
	@Column(DBHelper.TABLE_USER_PASSWORD)
	private String password;
	
	
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [_id=" + _id + ", username=" + username + ", password="
				+ password + "]";
	}
	
	
}
