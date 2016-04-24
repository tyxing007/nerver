package cn.tm.ms.nerver.route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tm.ms.nerver.common.Constants;
import cn.tm.ms.nerver.common.utils.CollectionUtils;
import cn.tm.ms.nerver.route.entry.NerverRequest;
import cn.tm.ms.nerver.route.entry.NerverRespone;
import cn.tm.ms.nerver.route.entry.RouteRule;
import cn.tm.ms.nerver.route.router.DeleteRouter;
import cn.tm.ms.nerver.route.router.GetRouter;
import cn.tm.ms.nerver.route.router.HeadRouter;
import cn.tm.ms.nerver.route.router.OptionsRouter;
import cn.tm.ms.nerver.route.router.PatchRouter;
import cn.tm.ms.nerver.route.router.PostRouter;
import cn.tm.ms.nerver.route.router.PutRouter;
import cn.tm.ms.nerver.route.router.TraceRouter;
import cn.tm.ms.nerver.route.type.MethodType;

/**
 * 路由模块
 * @author lry
 */
public enum RouteModuler {

	INSTANCE;
	
	/**
	 * 路由排序规则
	 */
	public static final String ROUTERULE_KEY="order";
	/**
	 * 原服务器地址分隔符
	 */
	public static final String URLS_SEQ_KEY=",";
	
	/**
	 * key为规则,value为待路由列表
	 */
	private Map<String,String[]> routeRules=new HashMap<String,String[]>();
	
	/**
	 * 添加路由规则
	 * @param rule
	 */
	public void addRouteRule(RouteRule rule) {
		String key=rule.getRule();
		if(key.startsWith("/")){
			key=key.substring(1);
		}
		routeRules.put(key, rule.getOriginUrls().split(URLS_SEQ_KEY));
	}
	
	/**
	 * 添加路由规则
	 * @param rules
	 */
	public void addRouteRules(List<RouteRule> rules) {
		for (RouteRule rule:rules) {
			String key=rule.getRule();
			if(key.startsWith("/")){
				key=key.substring(1);
			}
			routeRules.put(key, rule.getOriginUrls().split(URLS_SEQ_KEY));
		}
	}
	
	/**
	 * 重置路由规则
	 * @param rules
	 */
	public void resetRouteRules(List<RouteRule> rules) {
		if(rules==null||rules.isEmpty()){
			throw new RuntimeException();
		}
		
		//排序
		rules=CollectionUtils.sort(rules, ROUTERULE_KEY);
		
		//清空
		routeRules.clear();
		
		//转化
		for (RouteRule rule:rules) {
			String key=rule.getRule();
			if(key.startsWith("/")){
				key=key.substring(1);
			}
			routeRules.put(key, rule.getOriginUrls().split(URLS_SEQ_KEY));
		}
	}
	
	/**
	 * 执行路由
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public NerverRespone doRoute(NerverRequest request) throws Exception {
		String pathInfo = request.getPathInfo();
		pathInfo=pathInfo.substring(Constants.ROUTE_PATH_VERSION.length());
		if(pathInfo.startsWith("/")){
			pathInfo=pathInfo.substring(1);
		}
		
		MethodType methodType=MethodType.UNKNOWN;
		try {
			String method = request.getMethod().toUpperCase();
			methodType=MethodType.valueOf(method);
		} catch (Throwable t) {
		}
		
		switch (methodType) {
		case GET:
			return new GetRouter().route(routeRules,pathInfo,request);
		case POST:
			return new PostRouter().route(routeRules,pathInfo,request);
		case PUT:
			return new PutRouter().route(routeRules,pathInfo,request);
		case DELETE:
			return new DeleteRouter().route(routeRules,pathInfo,request);
		case HEAD:
			return new HeadRouter().route(routeRules,pathInfo,request);
		case TRACE:
			return new TraceRouter().route(routeRules,pathInfo,request);
		case OPTIONS:
			return new OptionsRouter().route(routeRules,pathInfo,request);
		case PATCH:
			return new PatchRouter().route(routeRules,pathInfo,request);
		default:
			throw new RuntimeException("Unknown request type.");
		}
	}
	
}