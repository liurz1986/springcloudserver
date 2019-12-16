package com.kedacom.keda.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Controller
public class HelloController {
	@RequestMapping("/test")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("host", "http://blog.didispace.com");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
}
