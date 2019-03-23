package com.ssm.mf.wade.dao;

import com.ssm.mf.domain.WadeRightMain;

/**
 * @ClassName WadeRightMapper
 * @Description TODO 涉权
 * @Author cfl
 * @Date 2019/3/7 14:48
 * @Version 1.0
 */
public interface WadeRightMapper {

	// 【主责部门】涉权模块第一个主页wadeRightMain.jsp 涉权总数、交办、核查、公示、说明（不合规处理说明）
	WadeRightMain selectWadeRightMain();
	
	Integer selectNumber();
}
