package com.tiankui.reactService.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 来电缓存工具类
 * 
 * @author zhouao
 *
 */
public class PhoneCache {

	// 缓存Map
	private static Map<String, CacheContent> map = new HashMap<String, CacheContent>();
	private static PhoneCache localCache = new PhoneCache();

	private PhoneCache() {
	}

	public String getLocalCache(String key) {
		CacheContent cc = map.get(key);

		if (null == cc) {
			return null;
		}

		long currentTime = System.currentTimeMillis();

		if (cc.getCacheMillis() > 0 && currentTime - cc.getCreateTime() > cc.getCacheMillis()) {
			// 超过缓存过期时间,返回null
			map.remove(key);
			return null;
		} else {
			return cc.getElement();
		}
	}

	public void setLocalCache(String key, int cacheMillis, String value) {
		long currentTime = System.currentTimeMillis();
		CacheContent cc = new CacheContent(cacheMillis, value, currentTime);
		map.put(key, cc);
	}
	
	public void removePhoneCache(String key){
		map.remove(key);
	}

	public static PhoneCache getInStance() {
		return localCache;
	}

	class CacheContent {
		// 缓存生效时间
		private int cacheMillis;
		// 缓存对象
		private String element;
		// 缓存创建时间
		private long createTime;

		public int getCacheMillis() {
			return cacheMillis;
		}

		public void setCacheMillis(int cacheMillis) {
			this.cacheMillis = cacheMillis;
		}

		public String getElement() {
			return element;
		}

		public void setElement(String element) {
			this.element = element;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public CacheContent() {
			super();
		}

		public CacheContent(int cacheMillis, String element, long createTime) {
			super();
			this.cacheMillis = cacheMillis;
			this.element = element;
			this.createTime = createTime;
		}

	}
}
