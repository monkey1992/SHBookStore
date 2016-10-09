package com.xiaoyu.shbookstore.util;

import java.io.IOException;
import java.util.Properties;

public class BeanFactory {
	
	private static Properties props;
	static {
		props = new Properties();
		try {
			props.load(BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建具体的实现类
	 * @param <T> 接口类型
	 * @param clazz 字节码对象
	 * @return 返回指定的具体实现类,失败返回null
	 */
	public static <T> T getImpl(Class<T> clazz) {
		String key = clazz.getSimpleName();
		String className = props.getProperty(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
