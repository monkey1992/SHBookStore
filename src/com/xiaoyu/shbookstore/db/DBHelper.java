package com.xiaoyu.shbookstore.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NAME = "redbaby.db";

	private static final int START_VERSION = 1;
	private static final int HISTORY_VERSION = 2;
	private static final int CURRENT_VERSION = 3;

	public static final String TABLE_USER_NAME = "user";

	public static final String TABLE_ID = "_id";

	public static final String TABLE_USER_USERNAME = "username";

	public static final String TABLE_USER_PASSWORD = "password";

	public static final String TABLE_CART_NAME = "cart";//购物车表名

	public static final String TABLE_CART_PROD_ID = "prod_id";//商品ID

	public static final String TABLE_CART_PROD_NUM = "prod_num";//商品数量

	public static final String TABLE_CART_PROD_PROP = "prod_prop";//商品属性

	public DBHelper(Context context) {
		super(context, NAME, null, START_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_USER_NAME + "(" + TABLE_ID
				+ " integer primary key autoincrement," + TABLE_USER_USERNAME
				+ " varchar(50)," + TABLE_USER_PASSWORD + " varchar(50)" + ")");

		db.execSQL("CREATE TABLE " + TABLE_CART_NAME + " (" + TABLE_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_CART_PROD_ID
				+ " VARCHAR(10), " + TABLE_CART_PROD_NUM + " VARCHAR(10), "
				+ TABLE_CART_PROD_PROP + " VARCHAR(10))");
		db.execSQL("create table payment1 (_id integer primary key autoincrement,orderid varvhar(50),total varchar(100),username varchar(50),usercall varchar(50),place varchar(255),paytype varchar(50),sendtime varchar(50),msg varchar(255))");
		
		//db.execSQL("create table product (_id integer primary key autoincrement,proname varchar(255),propic varchar(255),proid varchar(50),price varchar(50),count varchar(50),totalprice varchar(255))");
		
	//	db.execSQL("create table invoice (_id integer primary key autoincrement,username varchar(50),title varchar(255),content varchar(50))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
