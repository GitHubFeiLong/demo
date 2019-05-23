package com.ssm.mf.page.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.mf.domain.PageDemo;
import com.ssm.mf.page.dao.PageMapper;
import com.ssm.mf.page.service.PageService;

@Service
public class PageServiceImpl implements PageService{

	@Autowired
	private PageMapper dao;
	
	@Override
	public PageInfo<PageDemo> getPage(Integer current, Integer rows) {
		Map map = new HashMap<Integer, Integer>();
		map.put("currPage", current);
		map.put("pageSize", rows);
		
		PageHelper.startPage(current, rows);
		List list= dao.selectPageByPage(map);
		PageInfo<PageDemo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
