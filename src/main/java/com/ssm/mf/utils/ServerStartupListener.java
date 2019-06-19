/**
 * 
 */
package com.ssm.mf.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**   
 * @ClassName:  ServerStartupListener   
 * @Description:TODO  容器启动时,将项目名绑定到应用上下文 
 * @author: cfl
 * @date:  2019年3月14日 下午3:09:36     
 */
public class ServerStartupListener implements ServletContextListener{


	public void contextInitialized(ServletContextEvent sce) {
		
		// 将web应用名称（路径）保存到application范围中
		ServletContext application = sce.getServletContext();
		String path = application.getContextPath();
		application.setAttribute("APP_PATH", path);
		
	}


	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
