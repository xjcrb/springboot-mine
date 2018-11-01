package com.netsense.cloudfilemanager.annotation;


import java.lang.annotation.*;

/**
 * 
 * @author gaozairui
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String description() default "";
}