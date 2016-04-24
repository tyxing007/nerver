package cn.tm.ms.nerver.restful.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 * <p><p>
 * 要求:<p>
 * 1.类上必须含有该注解<p>
 * 2.必须含有默认的构造函数<p>
 * <p>
 * @author lry
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ctrl {
	
	/**
	 * 控制器值
	 * @return
	 */
	public String value();
	
	/**
	 * 控制器说明信息
	 * @return
	 */
	public String msg() default "无";
	
} 
