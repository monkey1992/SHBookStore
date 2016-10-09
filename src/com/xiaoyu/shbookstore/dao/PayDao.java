package com.xiaoyu.shbookstore.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiaoyu.shbookstore.db.DBHelper;
import com.xiaoyu.shbookstore.domain.Payment;

public class PayDao {
	protected DBHelper helper;
	protected SQLiteDatabase db;
	protected Context context;
	
	public PayDao(Context context) {
		this.context = context;
		helper = new DBHelper(context);
		db = helper.getReadableDatabase();
	}
	public List<Payment> getPaymentlist(){
		List<Payment> list=new ArrayList<Payment>();
		Cursor cursor = db.query("payment1", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			Payment ment=new Payment();
			ment.setOrderid(cursor.getString(1));
			ment.setTotal(cursor.getString(2));
			ment.setUsername(cursor.getString(3));
			ment.setUsercall(cursor.getString(4));
			ment.setPaytype(cursor.getString(5));
			ment.setSendtime(cursor.getString(6));
			ment.setMsg(cursor.getString(7));
			list.add(ment);
		}
		return list;
	}
	
}
