/**
 * 
 */
package com.ssm.mf.wade.service;

import com.ssm.mf.domain.WadeRightMain;

/**   
 * @ClassName:  WadeRightService   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年3月12日 下午5:06:22     
 */
public interface WadeRightService {
	
	
	// 【主责部门】查询涉权 ： 涉权总数、交办、核查、公示、说明（不合规处理说明）
	WadeRightMain getWadeRightMain1();
	
	Integer getNumber();
	
}
