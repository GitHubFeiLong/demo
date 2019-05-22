package com.ssm.mf.page.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mf.domain.PageDemo;
import com.ssm.mf.page.dao.PageMapper;
import com.ssm.mf.page.service.PageService;

@Service
public class PageServiceImpl implements PageService{

	@Autowired
	private PageMapper dao;
	
	@Override
	public List<PageDemo> getPage(Integer page, Integer rows) {
		
		return dao.selectPage(page, rows);
	}

}
