package com.ssm.mf.aop.web;

import com.ssm.mf.aop.My;
import com.ssm.mf.aop.before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName AopController
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/15 12:58
 * @Version 1.0
 */
@Controller
public class AopController {
	
	@RequestMapping("/aop")
	@ResponseBody
	@before
	public String aop(){
		System.out.println("你好");
		return "aop";
	}
}
