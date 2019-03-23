/**
 * 
 */
package com.ssm.mf.wade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.mf.domain.WadeRightMain;
import com.ssm.mf.wade.service.WadeRightServiceImpl;

/**   
 * @ClassName:  WadeRightController   
 * @Description:TODO   涉权，主责和纪委/纪检组的区别在于路径后缀多一个T
 * @author: cfl
 * @date:  2019年3月12日 下午5:15:43     
 */
@Controller
public class WadeRightController {
	
	@Autowired
	private WadeRightServiceImpl service;
	
	/**
	 * 
	 * @Title: getWadeRight   
	 * @Description: TODO(查询涉权主页：涉权总数、已交办、已核查、已公示、不合规处理、说明(民主生活会说明))   
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	@RequestMapping("/getWadeRight")
	public String getWadeRight(Model model) {
		WadeRightMain wadeRightMain = service.getWadeRightMain1();
		model.addAttribute("wadeRightMain1", wadeRightMain);
		
		return "wadeRightMain";
	}
	

	@RequestMapping("/getNumber")
	@ResponseBody
	public Integer getNumber() {
		return service.getNumber();
	}
	
}
