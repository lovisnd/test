package com.tiankui.reactService.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tiankui.reactService.entity.DateOrder;
import com.tiankui.reactService.entity.Result;
 
/**
 * 缓存工具类
 * @author joke
 *
 */
public class LocalCache {
 
    //缓存Map
    private static Map<String,CacheContent> map = new HashMap<String,CacheContent>();
    private static  LocalCache localCache = new LocalCache();
 
    private LocalCache(){
    }
 
    public  Result<List<DateOrder>> getLocalCache(String key) {
        CacheContent cc = map.get(key);
 
        if(null == cc) {
            return null;
        }
 
        long currentTime = System.currentTimeMillis();
 
        if(cc.getCacheMillis() > 0 && currentTime - cc.getCreateTime() > cc.getCacheMillis()) {
            //超过缓存过期时间,返回null
            map.remove(key);
            return null;
        } else {
            return cc.getElement();
        }
    }
 
    public void setLocalCache(String key,int cacheMillis,Result<List<DateOrder>> value) {
        long currentTime = System.currentTimeMillis();
        CacheContent cc = new CacheContent(cacheMillis,value,currentTime);
        map.put(key,cc);
    }
 
    public static LocalCache getInStance(){
        return localCache;
    }
 

    class CacheContent{
        // 缓存生效时间
        private  int cacheMillis;
        // 缓存对象
        private Result<List<DateOrder>> element;
        // 缓存创建时间
        private long createTime ;
		public int getCacheMillis() {
			return cacheMillis;
		}
		public void setCacheMillis(int cacheMillis) {
			this.cacheMillis = cacheMillis;
		}
		public Result<List<DateOrder>> getElement() {
			return element;
		}
		public void setElement(Result<List<DateOrder>> element) {
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
			// TODO Auto-generated constructor stub
		}
		public CacheContent(int cacheMillis, Result<List<DateOrder>> element, long createTime) {
			super();
			this.cacheMillis = cacheMillis;
			this.element = element;
			this.createTime = createTime;
		}
 
    }
}

