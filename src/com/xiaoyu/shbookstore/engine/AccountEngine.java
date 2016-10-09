package com.xiaoyu.shbookstore.engine;

import java.util.List;

import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.domain.User;
import com.xiaoyu.shbookstore.domain.UserInfo;

public interface AccountEngine {	
	boolean login(User user);
	
	boolean register(User user);
	
	List<Product> getFavorite(String page, String pageNum);
	
	UserInfo getUserInfo();
}
