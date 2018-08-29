package com.tiankui.reactService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo{
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date parse = sdf.parse("2018-05-09 16:11:11");
		long time = parse.getTime();
		System.out.println(time);
	}
}