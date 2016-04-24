package cn.tm.ms.nerver.filter.type;

public enum FilterType {

	/**
	 * 预处理过滤器
	 */
	PRE,
	
	/**
	 * 路由器前过滤器
	 */
	ROUTE,
	
	/**
	 * 路由后过滤器
	 */
	RECV,
	
	/**
	 * 返回前过滤器
	 */
	RET,
	
	/**
	 * 异常类过滤器
	 */
	ERROR;
	
}
