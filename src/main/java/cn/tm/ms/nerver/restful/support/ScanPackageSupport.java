package cn.tm.ms.nerver.restful.support;

import java.lang.reflect.Method;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.tm.ms.nerver.common.ScanPackage;
import cn.tm.ms.nerver.restful.annotation.API;
import cn.tm.ms.nerver.restful.annotation.Ctrl;
import cn.tm.ms.nerver.restful.entry.MethodNode;

/**
 * 扫描支持
 * @author lry
 */
public enum ScanPackageSupport {
	
	INSTANCE;
	
	private static final Logger logger=Logger.getLogger(ScanPackageSupport.class);
	
	public void scan(String scanPath) {
		Set<Class<?>> set=ScanPackage.getClasses(scanPath);
		if(set==null||set.isEmpty()){
			return;
		}
		
		for (Class<?> clazz:set) {
			Ctrl ctrl=clazz.getAnnotation(Ctrl.class);
			if(ctrl!=null){//选择含有@Ctrl注解的class
				try {
					Object obj=clazz.newInstance();//实例化默认构造函数
					Method[] methods=clazz.getDeclaredMethods();
					if(methods==null||methods.length<1){
						return;
					}
					for (Method method:methods) {
						API api=method.getAnnotation(API.class);
						if(api!=null){//选择含有@API注解的方法
							String apiNodes=api.value();
							if(apiNodes.startsWith("/")){
								apiNodes=apiNodes.substring(1);
							}
							if(apiNodes.endsWith("/")){
								apiNodes=apiNodes.substring(apiNodes.length()-1);
							}
							String[] methodNodes=apiNodes.split("/");
							if(methodNodes.length>0){
								MethodNode node=null;
								for (int i=methodNodes.length;i>0;i--) {
									MethodNode tempNode=new MethodNode(methodNodes[i-1], method);
									if(i==methodNodes.length){
										tempNode.setMethod(method);
										node=tempNode;
									}else{
										tempNode.setMethodNode(node);
										node=tempNode;
									}
								}
							}
						}
					}
					System.out.println(obj);
				} catch (Throwable t) {
					logger.error("The '"+clazz.getName()+"' new instance is failure.");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		INSTANCE.scan("cn.tm.ms.nerver.restful");
	}
	
}
