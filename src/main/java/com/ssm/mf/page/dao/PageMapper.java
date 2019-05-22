package com.ssm.mf.page.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.mf.domain.PageDemo;

public interface PageMapper {

	List<PageDemo> selectPage(@Param("page")Integer page, @Param("rows")Integer rows);

}
