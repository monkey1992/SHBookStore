package com.xiaoyu.shbookstore.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 软引用的map集合
 * @param <K>
 * @param <V>
 */
public class SoftMap<K,V> extends HashMap<K, V> {
	//降低对象的引用级�?--V	
	private HashMap<K, SoftValue<K, V>> temp;
	//应用对象的排队的容器，与垃圾回收机制相关
	private ReferenceQueue<V> queue;  
	
	/**
	 * 
	 */
	public SoftMap() {
//		Object valuel;   //占用内润较多
//		SoftReference sr = new SoftReference(valuel);  //value的引用级别降低了
		temp = new HashMap<K, SoftValue<K,V>>();
		queue = new ReferenceQueue<V>();
	}
	
	@Override
	public V put(K key, V value) {
		SoftValue<K,V> sr = new SoftValue<K,V>(key, value, queue); //包裹了对�?
		temp.put(key, sr);		
		return null;
	}
	
	@Override
	public boolean containsKey(Object key) {	
		return get(key)!=null;	
	}
	
	@Override
	public V get(Object key) {
		clearSr();
		SoftValue<K,V> sr = temp.get(key);
		if(sr != null) {
			return sr.get();   //很有可能拿到就是为空
		}
		return null;
	}
	
	/**
	 * 清理空袋
	 */
	public void clearSr() {
		SoftValue<K,V> poll = (SoftValue<K,V>) queue.poll();
		while(poll != null) {
			temp.remove(poll.key);
			poll = (SoftValue<K,V>) queue.poll();
		}
	}
	
	private class SoftValue<K,V> extends SoftReference<V>{
		private Object key;	
		
		/**
		 * 通过软引用降低对象的引用级别
		 * @param key 
		 * 			key
		 * @param r
		 * 			value
		 * @param q
		 * 			queue
		 */
		public SoftValue(K key, V r, ReferenceQueue<? super V> q) {
			super(r, q);
			this.key = key;
		}
	}
}
