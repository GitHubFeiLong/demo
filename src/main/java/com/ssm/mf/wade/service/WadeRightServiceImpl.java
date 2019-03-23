/**
 * 
 */
package com.ssm.mf.wade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mf.domain.WadeRightMain;
import com.ssm.mf.wade.dao.WadeRightMapper;

/**   
 * @ClassName:  WadeRightServiceImpl   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年3月12日 下午5:07:44     
 */
@Service
public class WadeRightServiceImpl implements WadeRightService{

	@Autowired
	private WadeRightMapper dao;
	
	
	// 【主责部门】查询涉权 ： 涉权总数、交办、核查、公示、说明（不合规处理说明） 
	public WadeRightMain getWadeRightMain1() {
		
		return dao.selectWadeRightMain();
	}


	public Integer getNumber() {
		return dao.selectNumber();
	}
	
	
	

	

}
