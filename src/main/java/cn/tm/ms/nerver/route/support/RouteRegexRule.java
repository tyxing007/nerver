package cn.tm.ms.nerver.route.support;

import java.util.Map;

/**
 * URL 路由匹配规则
 * @author lry
 */
public class RouteRegexRule {

	public static final String ANY_SEQ="*";
	
	public String[] regex(String pathInfo,Map<String,String[]> routeRules) {
		for (Map.Entry<String, String[]> entry:routeRules.entrySet()) {
			String key=entry.getKey();
			while (key.endsWith(ANY_SEQ)) {
				key=key.substring(0, key.length()-1);
			}
			if(pathInfo.startsWith(key)){
				return entry.getValue();
			}
		}
		return null;
	}
}
