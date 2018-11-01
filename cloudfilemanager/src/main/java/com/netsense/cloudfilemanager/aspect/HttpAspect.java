package com.netsense.cloudfilemanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.netsense.cloudfilemanager.controller..*.*(..)) || @annotation(com.netsense.cloudfilemanager.annotation.SysLog)")
    public void log(){

    }

    @Before("log()")
    public void dobefore(JoinPoint joinPoint){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()+"."+ joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());

        System.out.println("before========================");
    }

    @After("log()")
    public void doafter(){
        logger.info("doafter========================");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doafterrunning(Object object){
        logger.info("response={}",object.toString());
    }
}
