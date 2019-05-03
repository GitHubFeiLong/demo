package com.ssm.mf.uploadfile.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mf.domain.File;
import com.ssm.mf.domain.Student;
import com.ssm.mf.uploadfile.dao.FileMapper;
import com.ssm.mf.uploadfile.service.FileService;
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileMapper dao;

	/**
	 * 添加文件
	 */
	public void addFile(File file) {
		dao.insertFile(file);
	}

	/**
	 * 根据id查询student
	 */
	@Override
	public Student getStudentById(Integer id) {
		Student stu = dao.selectStudentById(id);
		return stu;
	}
	
}
