package cn.tm.ms.nerver.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tm.ms.nerver.filter.support.FilterFactory;
import cn.tm.ms.nerver.filter.sysfilter.RouteFilter;
import cn.tm.ms.nerver.filter.type.FilterType;

/**
 * 过滤模块
 * 
 * @author lry
 */
public enum FilterModuler {
	
	INSTANCE;

	private FilterFactory factory=FilterFactory.INSTANCE;
	
	/**
	 * 错误来源KEY
	 * <p>
	 * 获取方式:request.getAttribute("orginError")
	 */
	public static final String ORGINERROR_KEY="orginError";
	
	/**
	 * 初始化
	 * 
	 * @throws Throwable
	 */
	public void init() throws Throwable {
		init(true);
	}
	
	/**
	 * 初始化
	 * 
	 * @param isSysRouterFilter
	 * @throws Throwable
	 */
	public void init(boolean isSysRouterFilter) throws Throwable {
		if(isSysRouterFilter){
			factory.addFilter(new RouteFilter());
		}
	}
	
	/**
	 * 执行过滤
	 * 
	 * @param request
	 * @param response
	 * @throws Throwable
	 */
	public void filter(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		try {
			try {
				// 第一阶段:预处理
				preRoute(request, response);
			} catch (Throwable t) {
				errorRoute(FilterType.PRE,request, response, t);
				return;
			}

			try {
				// 第二阶段:路由阶段
				route(request, response);
			} catch (Throwable t) {
				errorRoute(FilterType.ROUTE,request, response, t);
				return;
			}

			try {
				// 第三阶段:接收阶段
				recvRoute(request, response);
			} catch (Throwable t) {
				errorRoute(FilterType.RECV,request, response, t);
				return;
			}

			try {
				// 第四阶段:返回阶段
				retRoute(request, response);
			} catch (Throwable t) {
				errorRoute(FilterType.RET,request, response, t);
				return;
			}
		} catch (Throwable t) {
			errorRoute(FilterType.ERROR,request, response, t);
		}
	}

	/**
	 * 第一阶段:预处理 请求达到之前执行
	 * 
	 * @param response
	 * @param request
	 */
	private void preRoute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		factory.doFilter(FilterType.PRE, request, response);
	}

	/**
	 * 第二阶段:路由阶段 请求转发至远程服务器之前执行
	 * 
	 * @param response
	 * @param request
	 */
	private void route(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		factory.doFilter(FilterType.ROUTE, request, response);
	}

	/**
	 * 第三阶段:接收阶段 请求从远程服务器执行完毕回来之前执行
	 * 
	 * @param response
	 * @param request
	 */
	private void recvRoute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		factory.doFilter(FilterType.RECV, request, response);
	}

	/**
	 * 第四阶段:返回阶段
	 * 
	 * @param request
	 * @param response
	 */
	private void retRoute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		factory.doFilter(FilterType.RET, request, response);
	}

	/**
	 * 第五阶段:异常 请求发生异常时执行
	 * 
	 * @param request
	 * @param response
	 * @param t
	 */
	private void errorRoute(FilterType filterType,HttpServletRequest request, HttpServletResponse response, Throwable t) {
		try {
			request.setAttribute(ORGINERROR_KEY, filterType);
			factory.doFilter(FilterType.ERROR, request, response);
		} catch (Throwable e) {
			//go on
		}
	}

}
