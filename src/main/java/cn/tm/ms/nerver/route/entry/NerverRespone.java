package cn.tm.ms.nerver.route.entry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

public class NerverRespone {

	/**
	 * 状态码
	 */
	private int statusCode=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	/**
	 * 响应数据
	 */
	private byte[] data;
	/**
	 * 响应头
	 */
	private Map<String,String> headers=new ConcurrentHashMap<String,String>();
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public void addHeader(String key,String value) {
		this.headers.put(key, value);
	}
	
}
