package com.ssm.mf.uploadfile.dao;

import org.apache.ibatis.annotations.Param;

import com.ssm.mf.domain.File;
import com.ssm.mf.domain.Student;

public interface FileMapper {
	// 添加文件路径到数据库
	void insertFile(@Param("file")File file);
	
	//查询student
	Student selectStudentById(Integer id);
}
