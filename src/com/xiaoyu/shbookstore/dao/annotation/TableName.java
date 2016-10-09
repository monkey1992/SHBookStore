package com.xiaoyu.shbookstore.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定了实体与数据库中表的对应关系
 * 
 * @author Administrator
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
	/**
	 * 数据库中表名
	 * 
	 * @return
	 */
	String value();
}
