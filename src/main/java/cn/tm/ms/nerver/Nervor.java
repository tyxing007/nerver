package cn.tm.ms.nerver;

import cn.tm.ms.nerver.container.IContainer;
import cn.tm.ms.nerver.container.JettyContainer;
import cn.tm.ms.nerver.route.RouteModuler;
import cn.tm.ms.nerver.route.entry.RouteRule;

/**
 * 微服务神经
 * @author lry
 */
public enum Nervor {
	
	INSTANCE;
	
	public RouteModuler routeModuler=RouteModuler.INSTANCE;

	public IContainer container=null;
	
	public void start() {
		try {
			//添加路由规则
			routeModuler.addRouteRule(new RouteRule(0, true, "api/get/**", "http://127.0.0.1:8081/api/get"));
			
			container=new JettyContainer();
			container.cstart(8080);
		} catch (Throwable t) {
			if(container!=null){
				container.cstop();
			}
			t.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		INSTANCE.start();
	}
}
