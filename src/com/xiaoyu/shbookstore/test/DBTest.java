package com.xiaoyu.shbookstore.test;

import android.test.AndroidTestCase;

import com.xiaoyu.shbookstore.dao.PayDao;

public class DBTest extends AndroidTestCase {
	/**
	 * 
	 */
	public void test(){
//		DBHelper helper=new DBHelper(getContext());
//		SQLiteDatabase database = helper.getWritableDatabase();
//		//database.query("", columns, selection, selectionArgs, groupBy, having, orderBy)
		
//		Map<String,String> map=new HashMap<String, String>();;
//		map.put("1", "2");
//		map.put("2", "2");
//		map.put("3", "2");
//		map.put("1", "3");
//		System.out.println(map);
	
		PayDao dao=new PayDao(getContext());
		dao.getPaymentlist();
	}
}
