package com.tiankui.reactService.util;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUID(){
		String replace = UUID.randomUUID().toString().replace("-", "");
		return replace;
	}
}
