package com.ssm.mf.page.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssm.mf.domain.PageDemo;
import com.ssm.mf.domain.StudentSqlserver;

public interface PageMapper {

	List<PageDemo> selectPageByPage(Map map);

	/**
	 * 添加student
	 * @param list
	 */
	void insertStudent(List<StudentSqlserver> list);

	/**
	 * 分页查询student
	 * @return
	 */
	List<StudentSqlserver> selectAllStudent();

}
