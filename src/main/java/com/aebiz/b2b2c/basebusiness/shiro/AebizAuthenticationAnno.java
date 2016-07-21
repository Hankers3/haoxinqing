package com.aebiz.b2b2c.basebusiness.shiro;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用权限的自定义注解
 * 
 * @author cc
 * 
 */
@Target(value = { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AebizAuthenticationAnno {
	public String expression();
}