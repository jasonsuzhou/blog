package com.mh.blog.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String date2String(Date date) {
		if (date == null) {
			return "";
		}
		try {
			DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

}
