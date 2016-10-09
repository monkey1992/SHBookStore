package com.xiaoyu.shbookstore.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import android.util.Log;

import com.xiaoyu.shbookstore.config.ConstantValue;

public  class CartTest {

	private static final String TAG = "CartTest";
	private static HashMap<String,String> map;

	public static HashMap<String, String> Connection() {
		try {
			URL url = new URL(ConstantValue.COMMON_URI+ConstantValue.CART);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(2000);
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				String result = StreamTools.readStream(is);

				JSONObject jsonObj = new JSONObject(result);
				Object object = jsonObj.get("product");
				
				String substringBetween = StringUtils.substringBetween(object.toString().trim(), "{", "}");
				map = new HashMap<String, String>();
				String[] split = substringBetween.split(",");
				for (String string : split) {
					String[] split2 = string.split(":");
					map.put(split2[0], split2[1]);
				}
				Log.i(TAG, "数据为哈哈哈哈哈"+map.toString());
				
				return map;
			}
			Log.i(TAG, code+"状态吗是呵呵呵呵呵");

		} catch (Exception e) {
			e.printStackTrace();
			Log.i(TAG,"报错了谢谢谢谢洗涤");
		}
		return null;
	}

	private static class StreamTools {

		/**
		 * 工具方法 把流里面的内容转换成字符串
		 * 
		 * @param is
		 * @return
		 * @throws IOException
		 */
		private static String readStream(InputStream is) throws IOException {
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			is.close();
			String result = baos.toString();
			baos.close();
			return result.trim();
		}
	}

}
