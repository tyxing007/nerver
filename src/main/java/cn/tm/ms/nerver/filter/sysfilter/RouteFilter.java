package cn.tm.ms.nerver.filter.sysfilter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tm.ms.nerver.filter.entry.NerverFilter;
import cn.tm.ms.nerver.filter.support.FilterType;
import cn.tm.ms.nerver.route.RouteModuler;
import cn.tm.ms.nerver.route.entry.NerverRequest;
import cn.tm.ms.nerver.route.entry.NerverRespone;

/**
 * 系统路由过滤器
 * order=1000000
 * @author lry
 */
public class RouteFilter implements NerverFilter {

	public String filterName() {
		return RouteFilter.class.getName();
	}
	
	public boolean isFilterDisabled() {
		return true;
	}

	public FilterType filterType() {
		return FilterType.ROUTE;
	}

	public int filterOrder() {
		return 1000000;
	}

	public boolean shouldFilter(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	public Object run(HttpServletRequest request, HttpServletResponse response) {
		NerverRespone nerverRespone=null;
		try {
			NerverRequest  nerverRequest=new NerverRequest().convert(request);
			nerverRespone=RouteModuler.INSTANCE.doRoute(nerverRequest);

			//校验
			if(nerverRespone==null){
				nerverRespone=new NerverRespone();
				nerverRespone.setData("Routr is failure.".getBytes());
			}
			
			
			//响应请求头
			response.setIntHeader("route_StatusCode:", nerverRespone.getStatusCode());
			if(!nerverRespone.getHeaders().isEmpty()){
				for (Map.Entry<String, String> entry:nerverRespone.getHeaders().entrySet()) {
					response.setHeader(entry.getKey(), entry.getValue());
				}
			}
			//响应状态码
			response.setStatus(HttpServletResponse.SC_OK);
			//响应数据
			response.getOutputStream().write(nerverRespone.getData());
		} catch (Throwable t) {
			try {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getOutputStream().write(("Failure code is "+HttpServletResponse.SC_INTERNAL_SERVER_ERROR+", error: "+t.getMessage()).getBytes());
				t.printStackTrace();
			} catch (IOException e) {
				//go on
			}
		}
		
		return null;
	}

}
