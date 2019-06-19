/**
 * 
 */
package com.ssm.mf.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**   
 * @ClassName:  MainJspFilter   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年5月17日 下午3:34:51     
 */
public class MainJspFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		HttpSession session = hRequest.getSession();
		String path = hRequest.getRequestURI();
	    String contextPath = hRequest.getContextPath();
	    String url = path.substring(contextPath.length());
	    String user = (String) session.getAttribute("user");
		// 继续执行
	    chain.doFilter(request, response);
	    if ("/login.jsp".equals(url)) {
	    	return;
	    }
	    if (user == null) {
	    	// 重定向登录页
	    	hResponse.sendRedirect(contextPath+"/login.jsp");
	        return;
	    }
		System.out.println("Main.jsp");
	
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	

}
