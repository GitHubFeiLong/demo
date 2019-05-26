package com.ssm.mf.utils;

import org.springframework.stereotype.Component;

/**
 * 动态设置数据源。
 * @author msi
 * @date 2019年5月26日
 */
@Component
public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	/**
	 * 设置连接数据源变量 
	 * @param dbType 数据源名
	 */
	public static void setDBType(String dbType) {
		contextHolder.set(dbType);
	}
	
	/**
	 *  得到数据源变量
	 * @return 数据源名字
	 */
	public static String getDBType() {
		return (String) contextHolder.get();
	}
	
	/**
	 * 清除设置过地数据源
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}
}
