package cn.tm.ms.nerver.filter.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tm.ms.nerver.common.utils.CollectionUtils;
import cn.tm.ms.nerver.filter.entry.NerverFilter;

/**
 * 过滤器管理工厂
 * @author lry
 */
public enum FilterFactory {

	INSTANCE;

	/**
	 * 过滤器集合
	 */
	public Map<FilterType,List<NerverFilter>> filters=new ConcurrentHashMap<FilterType,List<NerverFilter>>();
	
	public static final String SORT_KEY="filterType";
	
	/**
	 * 添加一个过滤器(自动排序)
	 * @param filter
	 */
	public void addFilter(NerverFilter filter) {
		List<NerverFilter> fList=filters.get(filter.filterType());
		if(fList==null){
			fList=new ArrayList<NerverFilter>();
		}
		fList.add(filter);
		
		//排序
		fList=CollectionUtils.sort(fList, SORT_KEY);
		
		//更新
		filters.remove(filter.filterType());
		filters.put(filter.filterType(), fList);
	}
	
	/**
	 * 添加多个过滤器
	 * @param filter
	 */
	public void addFilters(List<NerverFilter> filters) {
		for (NerverFilter filter:filters) {
			addFilter(filter);
		}
	}
	
	/**
	 * 重新设置所有的过滤器
	 * @param filters
	 */
	public void resetFilters(List<NerverFilter> filters) {
		filters.clear();
		addFilters(filters);
	}
	
	/**
	 * 重新设置一类过滤器
	 * @param filterType
	 * @param filters
	 */
	public void resetFilters(FilterType filterType,List<NerverFilter> nerverFilters) {
		List<NerverFilter> filteds=new ArrayList<NerverFilter>();
		for (NerverFilter nerverFilter:nerverFilters) {
			if(nerverFilter.filterType()==filterType){
				filteds.add(nerverFilter);
			}
		}
		//排序
		filteds=CollectionUtils.sort(filteds, SORT_KEY);
				
		//更新
		filters.remove(filterType);
		filters.put(filterType, filteds);
	}
	
	/**
	 * 执行过滤器
	 * @param filterType
	 * @param request
	 * @param response
	 * @return
	 */
	public Object doFilter(FilterType filterType,HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<NerverFilter> runFilters=filters.get(filterType);
		
		try {
			if(runFilters==null){
				return null;
			}
			for (NerverFilter filter:runFilters) {
				if(filter.isFilterDisabled()){//验证开启状态
					if(filter.shouldFilter(request, response)){//验证执行开关
						filter.run(request, response);
					}
				}
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
		return null;
	}
	
}
