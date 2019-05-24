package com.ssm.mf.page.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.mf.domain.PageDemo;
import com.ssm.mf.page.service.PageService;
import com.ssm.mf.utils.DataFormat;

@Controller
public class PageController {

	@Autowired
	private PageService service;
	
	@RequestMapping("/getPage")
	@ResponseBody
	public List getPage(Integer page, Integer rows) {
		List<PageDemo> list = service.getPage(page,rows);
		//得到分页的结果对象
		PageInfo<PageDemo> personPageInfo = new PageInfo<>(list);
		//得到分页中的person条目对象
	    List<PageDemo> pageList = personPageInfo.getList();
        System.out.println(list);
		return pageList;
	}
}
