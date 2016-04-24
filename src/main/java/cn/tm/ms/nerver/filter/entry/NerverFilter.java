package cn.tm.ms.nerver.filter.entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tm.ms.nerver.filter.type.FilterType;

/**
 * 过滤器
 * @author lry
 */
public interface NerverFilter {

	/**
	 * 过滤器名称
	 * @return
	 */
	public String filterName();
	
	/**
	 * 过滤器开关
	 * @return
	 */
	public boolean isFilterDisabled();
	
	/**
	 * 过滤器类型
	 * @return
	 */
	public FilterType filterType();
	
	/**
	 * 过滤器序号(从小到大依次执行过滤)
	 * @return
	 */
    public int filterOrder();
    
    /**
     * 是否该执行过滤
     * @param request
     * @param response
     * @return
     */
    public boolean shouldFilter(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 过滤逻辑处理
     * @param request
     * @param response
     * @return
     */
    public Object run(HttpServletRequest request, HttpServletResponse response);
    
}
