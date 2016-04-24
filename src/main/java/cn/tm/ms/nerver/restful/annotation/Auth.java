package cn.tm.ms.nerver.restful.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 * @author lry
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	/**
	 * 应用ID
	 * @return
	 */
	public String appid() default "";
	
	/**
	 * 应用token
	 * @return
	 */
	public String token() default "";
	
}
