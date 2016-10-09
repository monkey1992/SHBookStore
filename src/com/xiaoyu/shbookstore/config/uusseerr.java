package com.xiaoyu.shbookstore.config;

import android.content.Context;

import com.xiaoyu.shbookstore.dao.UserDaoImpl;
import com.xiaoyu.shbookstore.domain.User;

public class uusseerr {
	private Context ct;
	UserDaoImpl userDaoImpl = new UserDaoImpl(ct);
	public void main () {
		userDaoImpl.insert(new User());
	}
}
