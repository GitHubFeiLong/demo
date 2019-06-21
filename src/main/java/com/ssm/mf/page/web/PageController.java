package com.ssm.mf.page.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.mf.domain.PageDemo;
import com.ssm.mf.domain.StudentSqlserver;
import com.ssm.mf.page.service.PageService;
import com.ssm.mf.utils.DataFormat;
import com.ssm.mf.utils.DataSourceContextHolder;

@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	private PageService service;
	
	@RequestMapping("/getPage")
	@ResponseBody
	public Object getPage(Integer page, Integer rows) {
		DataSourceContextHolder.clearDBType();
		List<PageDemo> list = service.getPage(page,rows);
		//得到分页的结果对象
		PageInfo<PageDemo> personPageInfo = new PageInfo<>(list);
		Map<String, Object> map = new HashMap<String, Object>();
		//得到分页中的person条目对象
	    List<PageDemo> pageList = personPageInfo.getList();
	    System.out.println("控制器" + list.size());
        map.put("rows", list);
        map.put("total", personPageInfo.getTotal());
		return map;
	}
	
	@RequestMapping("/addStudent")
	@ResponseBody
	public String addStudent() {
		DataSourceContextHolder.setDBType("sqlserverDataSource");
		Random random = new Random(20);
		List<StudentSqlserver> list = new ArrayList<StudentSqlserver>();
		for (int i = 10; i < 100; i ++) {
			StudentSqlserver stu = new StudentSqlserver();
			stu.setAge(i + (int)(Math.random() * 10));
			System.out.println(stu.getAge());
			stu.setAddress("address" + i);
			stu.setName("name" + i);
			list.add(stu);
		}
		service.addStudent(list);
		
		return "成功";
	}
	
	@RequestMapping("/getStudent")
	@ResponseBody
	public Object listStudent(Integer page, Integer rows) {
		DataSourceContextHolder.setDBType("sqlserverDataSource");
		List<StudentSqlserver> list = service.listStudentSqlserver(page,rows);
		//得到分页的结果对象
		PageInfo<StudentSqlserver> personPageInfo = new PageInfo<>(list);
		Map<String, Object> map = new HashMap<String, Object>();
		//得到分页中的person条目对象
	    List<StudentSqlserver> pageList = personPageInfo.getList();
	   
        map.put("rows", list);
        map.put("total", personPageInfo.getTotal());
		return map;
	}
}
