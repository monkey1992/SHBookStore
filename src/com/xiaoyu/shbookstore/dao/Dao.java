package com.xiaoyu.shbookstore.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<Bean> {
	
	/**
	 * 增加一条数据
	 * @param bean 数据对应的bean
	 * @return 插入的条数
	 */
	long insert(Bean bean);
	
	/**
	 * 删除某行数据,
	 * @param  根据id删除数据
	 * @return 影响的行数
	 */
	int delete(Serializable id);   //int long String
	
	/**
	 * 更新修改数据
	 * @param bean 包装新数据的对象
	 * @return 影响的行数
	 */
	int update(Bean bean);
	
	/**
	 * 查找所有
	 * @return 数据的集合
	 */
	List<Bean> findAll();
}
