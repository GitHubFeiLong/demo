package com.ssm.mf.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TestAOP
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/12 15:28
 * @Version 1.0
 */
@Aspect
@Component
public class TestAOP {
	@Pointcut(value="@annotation(com.ssm.mf.aop.before)")
	public void befo(){};
	
	@Pointcut(value = "execution(* com.ssm.mf.aop.web.AopController.aop())")
	public void centerUrl(){};
	
	
	@Before(value="befo()")
	public void my(){
		System.out.println("请求的路径地址位：");
	}
	
	@Before(value="centerUrl()")
	public void myq(){
		System.out.println("请求的路径地址位：");
	}
}
