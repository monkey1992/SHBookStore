package com.xiaoyu.shbookstore.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定了实体的字段与数据库中表中列的对应关系
 * 
 * @author Administrator
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * 数据库中列名
	 * 
	 * @return
	 */
	String value();

}
