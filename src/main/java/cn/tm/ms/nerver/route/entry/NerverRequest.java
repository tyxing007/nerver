package cn.tm.ms.nerver.route.entry;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求转换
 * @author lry
 */
public class NerverRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String DEFAULT_ENCODING="UTF-8";
	
	private String pathInfo;
	private String encoding=DEFAULT_ENCODING;
	private String contentType;
	
	private String localAddr;
	private String localName;
	private int localPort;
	
	private String method;
	/**
	 * 请求头
	 */
	private Map<String,String> headers=new ConcurrentHashMap<String,String>();
	/**
	 * 请求参数
	 */
	private Map<String,Object> parameters=new ConcurrentHashMap<String,Object>();
	private String queryString;
	
	private String remoteAddr;
	private String remoteHost;
	private int remotePort;
	
	private String requestedSessionId;
	
	private String serverName;
	private int serverPort;
	
	public NerverRequest() {
	}
	public NerverRequest convert(HttpServletRequest request) {
		try {
			String pathInfo = request.getPathInfo();
			if (pathInfo.endsWith("/")) {
				pathInfo = pathInfo.substring(0, pathInfo.lastIndexOf("/"));
			}
			this.setPathInfo(pathInfo);
			
			this.setEncoding(request.getCharacterEncoding());
			this.setContentType(request.getContentType());
			
			this.setLocalAddr(request.getLocalAddr());
			this.setLocalName(request.getLocalName());
			this.setLocalPort(request.getLocalPort());
			
			this.setMethod(request.getMethod());
			this.setQueryString(request.getQueryString());
			
			this.setRemoteAddr(request.getRemoteAddr());
			this.setRemoteHost(request.getRemoteHost());
			this.setRemotePort(request.getRemotePort());
			
			this.setRequestedSessionId(request.getRequestedSessionId());
			
			this.setServerName(request.getServerName());
			this.setServerPort(request.getServerPort());
			
			Enumeration<String> headerKeys=request.getHeaderNames();
			while (headerKeys.hasMoreElements()) {
				String headerKey = headerKeys.nextElement();
				this.headers.put(headerKey, request.getHeader(headerKey));
			}
			
			Enumeration<String> parameterKeys=request.getParameterNames();
			while (parameterKeys.hasMoreElements()) {
				String parameterKey = parameterKeys.nextElement();
				this.parameters.put(parameterKey, request.getParameter(parameterKey));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return this;
	}

	public String getPathInfo() {
		return pathInfo;
	}

	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getLocalAddr() {
		return localAddr;
	}

	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public String getRequestedSessionId() {
		return requestedSessionId;
	}

	public void setRequestedSessionId(String requestedSessionId) {
		this.requestedSessionId = requestedSessionId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String toString() {
		return "NerverRequest [pathInfo=" + pathInfo + ", encoding=" + encoding + ", contentType="
				+ contentType + ", localAddr=" + localAddr + ", localName=" + localName + ", localPort=" + localPort
				+ ", method=" + method + ", headers=" + headers + ", parameters=" + parameters + ", queryString="
				+ queryString + ", remoteAddr=" + remoteAddr + ", remoteHost=" + remoteHost + ", remotePort=" + remotePort 
				+ ", requestedSessionId=" + requestedSessionId + ", serverName=" + serverName + ", serverPort=" + serverPort + "]";
	}
	
}	
