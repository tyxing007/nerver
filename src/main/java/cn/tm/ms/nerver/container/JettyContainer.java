package cn.tm.ms.nerver.container;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import cn.tm.ms.nerver.common.Constants;
import cn.tm.ms.nerver.filter.FilterModuler;

/**
 * Jetty容器
 * @author lry
 */
public class JettyContainer extends AbstractHandler implements IContainer {

	private static final Logger logger=Logger.getLogger(JettyContainer.class);
	
	private FilterModuler filterModuler=FilterModuler.INSTANCE;
	
	/**
	 * 启动
	 * @param port
	 * @throws Throwable
	 */
	public void cstart(int port) {
		if(logger.isInfoEnabled()){
			logger.info("Startting Nerver server...");
		}
		try {
			//初始化过滤器
			filterModuler.init();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		try {
			//启动
			Server server = new Server(port);
			server.setHandler(this);
			server.start();
			if(logger.isInfoEnabled()){
				logger.info("Started Nerver server is successed.");
			}
			server.join();
		} catch (Throwable t) {
			logger.error("Started Nerver server is failure, error "+t.getMessage(),t);
			t.printStackTrace();
		}
	}
	
	/**
	 * 销毁
	 * @throws Throwable
	 */
	public void cstop() {
		
	}
	
	/**
	 * 请求处理
	 */
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			request.setCharacterEncoding(Constants.DEFAULT_ENCODING);
			response.setCharacterEncoding(Constants.DEFAULT_ENCODING);
			response.setContentType("text/html; charset="+Constants.DEFAULT_ENCODING);
			response.setHeader("Content-Type", "text/html; charset="+Constants.DEFAULT_ENCODING);
			
			if(target.startsWith(Constants.ROUTE_PATH_BASE)){
				filterModuler.filter(request, response);
			}else{
		        response.setStatus(HttpServletResponse.SC_OK);  
		        response.getWriter().println("This is mvc moduler.");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
			baseRequest.setHandled(true);
		}
	}
	
}
