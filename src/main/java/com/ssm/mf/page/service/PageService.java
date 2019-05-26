package com.ssm.mf.page.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ssm.mf.domain.PageDemo;
import com.ssm.mf.domain.StudentSqlserver;

public interface PageService {

	/**
	 * 查询page，测试mysql分页
	 * @param page
	 * @param rows
	 * @return
	 */
	List<PageDemo> getPage(Integer page, Integer rows);

	/**
	 * 插入sqlserver数据库
	 * @param list
	 */
	void addStudent(List<StudentSqlserver> list);
	
	/**
	 * 查询student(sqlserver)测试sqlserver分页
	 * @return
	 */
	List<StudentSqlserver> listStudentSqlserver(Integer page, Integer rows);

}
