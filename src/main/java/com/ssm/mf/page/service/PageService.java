package com.ssm.mf.page.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ssm.mf.domain.PageDemo;

public interface PageService {

	List<PageDemo> getPage(Integer page, Integer rows);

}
