package cn.tm.ms.nerver.route.type;

/**
 * HTTP请求类型
 * @author lry
 */
public enum MethodType {
	
	/**
	 * GET请求
	 */
	GET, 
	
	/**
	 * HEAD请求
	 */
	HEAD, 
	
	/**
	 * POST请求
	 */
	POST, 
	
	/**
	 * PUT请求
	 */
	PUT, 
	
	/**
	 * DELETE请求
	 */
	DELETE, 
	
	/**
	 * TRACE请求
	 */
	TRACE, 
	
	/**
	 * OPTIONS请求
	 */
	OPTIONS, 
	
	/**
	 * PATCH请求
	 */
	PATCH,
	
	/**
	 * 未知请求类型
	 */
	UNKNOWN;
	
}
