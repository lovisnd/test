package com.tiankui.reactService.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.User;

public class LoginUserCache{
	
	public static Map<String,User> userCache = new HashMap<String,User>();
	public static final Long EXPIRE_TIME = 30L * 60 * 1000;

	
	//从缓存中获取用户登录信息，如果没登录或登录信息已经超过EXPIRE_TIME 返回null
	public static User get(String name){
		User user = null ;
		if(!userCache.containsKey(name)){
			return user;
		}
		user = userCache.get(name);
		
		if((new Date().getTime() - user.getTokenTime()) > EXPIRE_TIME){
			//登录信息过期
			userCache.remove(name);
			user = null;
		}
		
		return user;
	}
	
	public static void set(String key, User user){
		clear();
		userCache.put(key, user);
	}
	
	public static void remove(String key){
		if(userCache.containsKey(key)){
			userCache.remove(key);
		}
	}
	
	public static void clear(){
		List<String> keyList = new ArrayList<String>();
		if(userCache.size() < 1){
			return;
		}
		User user = null;
		long nowTime = new Date().getTime();
		for (Map.Entry<String , User> entry : userCache.entrySet()) {
			user = entry.getValue();
			if(nowTime - user.getTokenTime() > EXPIRE_TIME){
				keyList.add(entry.getKey());
			}
		}
		
		if(keyList.size() > 0){
			for (int i = 0; i < keyList.size(); i++) {
				if(userCache.containsKey(keyList.get(i))){
					userCache.remove(keyList.get(i));
				}
			}
		}
	}
	

}
