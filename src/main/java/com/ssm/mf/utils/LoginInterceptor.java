package com.ssm.mf.utils;

/**
 * 
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**   
 * @ClassName:  LoginInterceptor   
 * @Description:TODO   登录拦截器，如果用户没有登录，或登录超时就会重定向登录页面
 * @author: cfl
 * @date:  2019年5月7日 下午4:10:14     
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		// 用户登录后，在session种存储用户“user”；如果session种的键为user的值为空，就不再执行。重定向登陆页面
		String user = (String) request.getSession().getAttribute("user");
        // 获取配置文件中的ip属性
		String ip = ConfigResolver.parseFile("serverIp");
		
        // 重定向登陆页面
        if (user == null) {
            //String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
            //System.out.println("获取全路径（协议类型：//域名/项目名/命名空间/action名称?其他参数）url="+url);
            String prefix = request.getContextPath();
            int port = request.getServerPort();
            System.out.println("prefix:" + prefix);
            System.out.println("http://" + ip + ":"+port+prefix+"/login.jsp");
            // 重定向登陆页面
        	response.sendRedirect("http://" + ip + ":"+port+prefix+"/login.jsp");
        }
        // return false,就停止继续向下
        return user != null;
        //return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        System.out.println("LoginInterceptor, postHandle......");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("LoginInterceptor, afterCompletion......");
    }

    @Override
    public void afterConcurrentHandlingStarted(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("LoginInterceptor, afterConcurrentHandlingStarted......");
    }
	

}
