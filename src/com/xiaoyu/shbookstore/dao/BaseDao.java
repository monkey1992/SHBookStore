package com.xiaoyu.shbookstore.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiaoyu.shbookstore.dao.annotation.Column;
import com.xiaoyu.shbookstore.dao.annotation.ID;
import com.xiaoyu.shbookstore.dao.annotation.TableName;
import com.xiaoyu.shbookstore.db.DBHelper;

public abstract class BaseDao<Bean> implements Dao<Bean> {
	protected DBHelper helper;
	protected SQLiteDatabase db;
	protected Context context;
	
	public BaseDao(Context context) {
		this.context = context;
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}
	
	
	@Override
	public long insert(Bean bean) {
		ContentValues values = new ContentValues();
		fillData(bean,values);
		return db.insert(getTableName(), null, values);
	}

	private void fillData(Bean bean, ContentValues values) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field item : fields) {
			item.setAccessible(true);
			Column column = item.getAnnotation(Column.class);
			if(column != null) {
				try {
					String key = column.value();
					String value = item.get(bean).toString();
					// 如果该field是主键，并且是自增的，不能添加到集合中
					ID id = item.getAnnotation(ID.class);
					if(id != null) {
						continue;
					}else {
						values.put(key, value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public int delete(Serializable id) {
		return db.delete(getTableName(), DBHelper.TABLE_ID + " =?", new String[]{id.toString()});
	}

	@Override
	public int update(Bean bean) {
		ContentValues values = new ContentValues();
		fillData(bean, values);
		return db.update(getTableName(), values, DBHelper.TABLE_ID + " =?", new String[]{getId(bean)});
	}


	@Override
	public List<Bean> findAll() {
		List<Bean> list = null;
		Cursor cursor = db.query(getTableName(), null, null, null, null, null, null);
		
		if(cursor != null) {
			list = new ArrayList<Bean>();
			while(cursor.moveToNext()) {
				Bean bean = getInstance();
				fillBean(cursor,bean);
				list.add(bean);
			}
		}
		cursor.close();
		return list;
	}
	
	private void fillBean(Cursor cursor, Bean bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field item : fields) {
			item.setAccessible(true);
			
			Column column = item.getAnnotation(Column.class);
			if(column != null) {
				int index = cursor.getColumnIndex(column.value());
				String columnValue = cursor.getString(index);
				// Sets the value of the field in the specified object to the value.
				try {
					if(item.getType() == int.class) { 
						//从数据库拿到的都是字符串,要转换为自己需要的类型
						item.set(bean, Integer.parseInt(columnValue));
					}else if(item.getType() == String.class) {
						item.set(bean, columnValue);
					}else if(item.getType() == float.class) {
						item.set(bean, Float.parseFloat(columnValue));
					}else if(item.getType() == double.class) {
						item.set(bean, Double.parseDouble(columnValue));
					}else {
						item.set(bean, columnValue);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private String getId(Bean bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field item : fields) {
			item.setAccessible(true);
			ID id = item.getAnnotation(ID.class);
			if(id != null) {
				try {
					return item.get(bean).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	
	// 问题一：表名的获取
	private String getTableName() {
		Bean b = getInstance();

		TableName tableName = b.getClass().getAnnotation(TableName.class);
		if (tableName != null) {
			return tableName.value();
		}

		return "";
	}

	// 问题二：如何将实体中的数据，按照对应关系导入到数据库表中

	// 问题三：将数据表中列的数据，按照对应关系导入到实体中
	// 问题四：明确实体中主键，获取到主键中封装的值
	// 问题五：实体的对象创建
	private Bean getInstance() {

		// ①那个孩子调用的该方法——那个孩子在运行
		// 返回此 Object 的运行时类。
		Class clazz = this.getClass();
		// ②获取该孩子的父类（“支持泛型”的父类）
		Type superclass = clazz.getGenericSuperclass(); // com.ithm.dbhm28.dao.base.BaseDao<com.ithm.dbhm28.dao.domain.User>
		// 泛型实现接口（参数化的类型），规定了泛型的通用操作
		if (superclass != null && superclass instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) superclass)
					.getActualTypeArguments();
			try {
				return (Bean) ((Class) types[0]).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
