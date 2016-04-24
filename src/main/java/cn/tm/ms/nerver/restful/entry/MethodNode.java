package cn.tm.ms.nerver.restful.entry;

import java.lang.reflect.Method;

public class MethodNode {

	private String node;
	private Method method;
	private MethodNode methodNode;
	
	public MethodNode() {
	}
	public MethodNode(String node, Method method) {
		this.node = node;
		this.method = method;
	}
	public MethodNode(String node, Method method, MethodNode methodNode) {
		this.node = node;
		this.method = method;
		this.methodNode = methodNode;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public MethodNode getMethodNode() {
		return methodNode;
	}
	public void setMethodNode(MethodNode methodNode) {
		this.methodNode = methodNode;
	}
	
}
