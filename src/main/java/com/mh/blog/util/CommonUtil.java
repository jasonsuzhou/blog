package com.mh.blog.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static <T> boolean isListNotEmpty(List<T> list) {
		return !isListEmpty(list);
	}

	public static <T> boolean isListEmpty(List<T> list) {
		return list == null || list.isEmpty();
	}

	public static int getStartRow(int curPage, int perPage) {
		return (curPage - 1) * perPage;
	}

	public static String getPageQuerySQL(int curPage, int perPage, String tableName) {
		return "select * from " + tableName + " limit " + getStartRow(curPage, perPage) + "," + perPage;
	}

	public static String getDeleteSQL(String tableName) {
		return "delete from " + tableName + " where id=?";
	}

	public static String getDetailSQL(String tableName) {
		return "select * from " + tableName + " where id=?";
	}
	
	public static String getUpdateSQL(String tableName, String[] fields) {
		StringBuilder sb = new StringBuilder(32);
		sb.append("update ").append(tableName).append(" set ");
		for (String field : fields) {
			sb.append(field).append("=?").append(",");
		}
		sb = sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" where id=?");
		return sb.toString();
	}

	public static Object[] genSQLObjects(Object... objs) {
		int size = objs.length;
		Object[] args = new Object[size];
		int index = 0;
		for (Object obj : objs) {
			args[index] = obj;
			index++;
		}
		return args;
	}

	public static int[] genSQLTypes(int... types) {
		int size = types.length;
		int[] argTypes = new int[size];
		int index = 0;
		for (int argType : types) {
			argTypes[index] = argType;
			index++;
		}
		return argTypes;
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
