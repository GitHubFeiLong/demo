package com.ssm.mf.uploadfile.service;

import com.ssm.mf.domain.File;
import com.ssm.mf.domain.Student;

public interface FileService {

	void addFile(File file);
	
	Student getStudentById(Integer id);
}
