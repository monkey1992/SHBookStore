package com.xiaoyu.shbookstore.dao;

import android.content.Context;

import com.xiaoyu.shbookstore.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	public UserDaoImpl(Context context) {
		super(context);
	}
}
