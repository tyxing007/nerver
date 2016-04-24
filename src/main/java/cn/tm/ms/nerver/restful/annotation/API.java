package cn.tm.ms.nerver.restful.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口注解
 * @author lry
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface API {
	
	/**
	 * 接口名称
	 * @return
	 */
	public String name() default "";
	
	/**
	 * API访问值
	 * @return
	 */
	public String value();
	
	/**
	 * 接口说明信息
	 * @return
	 */
	public String msg() default "";
	
	
}
