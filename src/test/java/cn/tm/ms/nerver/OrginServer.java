package cn.tm.ms.nerver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.alibaba.fastjson.JSON;

/**
 * 被路由的服务器
 * @author lry
 */
public class OrginServer extends AbstractHandler {

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		
		response.getWriter().println("已经到达源服务器了!参数:"+JSON.toJSONString(request.getParameterMap()));
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);
		server.setHandler(new OrginServer());
		server.start();
		System.out.println("源服务器启动成功");
		
		server.join();
	}
}