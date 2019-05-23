package com.ssm.mf.page.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.mf.domain.PageDemo;

public interface PageMapper {

	List<PageDemo> selectPageByPage(Map map);

}
