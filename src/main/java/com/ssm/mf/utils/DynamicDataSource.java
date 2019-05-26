package com.ssm.mf.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取当前数据源
 * @author msi
 * @date 2019年5月26日
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DataSourceContextHolder.getDBType();
	}

}
