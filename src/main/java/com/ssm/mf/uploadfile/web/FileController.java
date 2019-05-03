package com.ssm.mf.uploadfile.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.mf.domain.Student;
import com.ssm.mf.uploadfile.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService service;
	private static final Logger log = Logger.getLogger(FileController.class);
	
	@RequestMapping("/upload")
	public String addFile(HttpServletRequest req,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws Exception {
		if (file != null) {
			String path  = req.getServletContext().getRealPath("/Content/");
			System.out.println("path:==" + path);
			String fileName = file.getOriginalFilename();
			File filepath = new File(path,fileName);
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdir();
			}
			file.transferTo(filepath);
		}
		//service.addFile(file);
		log.info("调用日志，来打印=========================");
		return "forward:hello.html";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "test";
	}
	
	@RequestMapping("/getStudentById")
	@ResponseBody
	public Student getStudentById(Integer id) {
		return service.getStudentById(id);
	}
}

