package com.mh.blog.cache;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mh.blog.entity.Article;
import com.mh.blog.entity.Link;
import com.mh.blog.entity.Profile;

public class CacheManager {
	private static final String KEY_FOOTER = "footerInfo";
	private static final String KEY_LATEST_NEWS = "latestNews";
	private static final String KEY_TOP_NEWS = "topNews";
	private static final String KEY_LINKS = "links";
	private static final String KEY_PROFILE = "profile";
	private static final String KEY_WORK_DISPLAY = "workDisplay";

	public static Map<String, Object> cacheMap = new HashMap<String, Object>();

	public static void resetAllCache(JdbcTemplate jdbcTemplate) {
		resetCachedFooter(jdbcTemplate);
		resetCachedLatestArticle(jdbcTemplate);
		resetCachedTopArticle(jdbcTemplate);
		resetCachedLinks(jdbcTemplate);
		resetCachedProfile(jdbcTemplate);
		resetCachedWorkDisplay(jdbcTemplate);
	}

	public static void resetCachedFooter(JdbcTemplate jdbcTemplate) {
		cacheMap.remove(KEY_FOOTER);
		getCachedFooterInfo(jdbcTemplate);
	}

	public static void resetCachedLatestArticle(JdbcTemplate jdbcTemplate) {
		cacheMap.remove(KEY_LATEST_NEWS);
		getCachedLatestNews(jdbcTemplate);
	}

	public static void resetCachedTopArticle(JdbcTemplate jdbcTemplate) {
		cacheMap.remove(KEY_TOP_NEWS);
		getCachedTopNews(jdbcTemplate);
	}

	public static void resetCachedLinks(JdbcTemplate jdbcTemplate) {
		cacheMap.remove(KEY_LINKS);
		getCachedLinks(jdbcTemplate);
	}

	public static void resetCachedProfile(JdbcTemplate jdbcTemplate) {
		cacheMap.remove(KEY_PROFILE);
		getCachedProfile(jdbcTemplate);
	}

	public static void resetCachedWorkDisplay(JdbcTemplate jdbcTemplate) {
		cacheMap.remove(KEY_WORK_DISPLAY);
		getCachedWorkDisplay(jdbcTemplate);
	}

	public static void addCachedFooter(String footer) {
		cacheMap.put(KEY_FOOTER, footer);
	}

	public static List<Article> getCachedLatestNews(JdbcTemplate jdbcTemplate) {
		List<Article> lsArticle = (List<Article>) cacheMap.get(KEY_LATEST_NEWS);
		if (lsArticle == null || lsArticle.isEmpty()) {
			String sql = "select * from article order by post_date desc limit 0,10";
			lsArticle = jdbcTemplate.query(sql, new Article());
			cacheMap.put(KEY_LATEST_NEWS, lsArticle);
		}
		return lsArticle;
	}

	public static List<Article> getCachedTopNews(JdbcTemplate jdbcTemplate) {
		List<Article> lsArticle = (List<Article>) cacheMap.get(KEY_TOP_NEWS);
		if (lsArticle == null || lsArticle.isEmpty()) {
			String sql = "select * from article order by total desc limit 0,10";
			lsArticle = jdbcTemplate.query(sql, new Article());
			cacheMap.put(KEY_TOP_NEWS, lsArticle);
		}
		return lsArticle;
	}

	public static String getCachedFooterInfo(JdbcTemplate jdbcTemplate) {
		String footer = (String) cacheMap.get(KEY_FOOTER);
		if (StringUtils.isBlank(footer)) {
			String querysql = "select content from footer";
			List<Map<String, Object>> lsContent = jdbcTemplate.queryForList(querysql);
			if (lsContent != null && !lsContent.isEmpty()) {
				footer = (String) lsContent.get(0).get("content");
				addCachedFooter(footer);
			} else {
				String insertSQL = "insert into footer(content) values('Not configure yet')";
				jdbcTemplate.execute(insertSQL);
				lsContent = jdbcTemplate.queryForList(querysql);
				footer = (String) lsContent.get(0).get("content");
				addCachedFooter(footer);
			}
		}
		return footer;
	}

	public static List<Link> getCachedLinks(JdbcTemplate jdbcTemplate) {
		List<Link> list = (List<Link>) cacheMap.get(KEY_LINKS);
		if (list == null || list.isEmpty()) {
			String sql = "select * from link";
			list = jdbcTemplate.query(sql, new Link());
			cacheMap.put(KEY_LINKS, list);
		}
		return list;
	}

	public static Profile getCachedProfile(JdbcTemplate jdbcTemplate) {
		Profile profile = (Profile) cacheMap.get(KEY_PROFILE);
		if (profile == null) {
			String sql = "select * from profile";
			profile = jdbcTemplate.query(sql, new Profile()).get(0);
			cacheMap.put(KEY_PROFILE, profile);
		}
		return profile;
	}

	public static List<Map<String, Object>> getCachedWorkDisplay(JdbcTemplate jdbcTemplate) {
		List<Map<String, Object>> list = (List<Map<String, Object>>) cacheMap.get(KEY_WORK_DISPLAY);
		if (null == list) {
			String sql = "select id, title, download_url,summary_view_url from article where category = ? or category = ? limit 0,5";
			Object[] args = new Object[] { "作品展示", "Work Display" };
			int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
			list = jdbcTemplate.queryForList(sql, args, argTypes);
			cacheMap.put(KEY_WORK_DISPLAY, list);
		}
		return list;
	}

	/*
	 * //better not involve html inside server side public static String
	 * getStringLinks(JdbcTemplate jdbcTemplate) { StringBuilder sb = new
	 * StringBuilder(); List<Link> list = getCachedLinks(jdbcTemplate); if (list
	 * != null && !list.isEmpty()) { for (Link link : list) {
	 * sb.append("<li><a target=\"_blank\" href=\""
	 * ).append(link.getUrl()).append("\">")
	 * .append(link.getName()).append("</a></li>"); } } return sb.toString(); }
	 */
}
