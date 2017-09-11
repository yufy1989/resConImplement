package com.asiainfo.resConImplement.controller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类说明：测试
 * 
 * @author lipj
 * @date 2016年8月5日 上午09:47:55
 */
@Controller
@RequestMapping("/testAsk")
public class testController {
	


	/**
	 * 功能描述：测试
	 * @author lipj
	 * @date 2016年8月5日 上午10:08:14
	 * @param @param model
	 * @param @param request
	 * @param @return 
	 * @return String
	 */
	@RequestMapping(value = "ask", method = RequestMethod.GET)
	public String getAuthentication(
			Model model,
			ServletRequest request) {
		return "index";
	}
	
	
	

}
