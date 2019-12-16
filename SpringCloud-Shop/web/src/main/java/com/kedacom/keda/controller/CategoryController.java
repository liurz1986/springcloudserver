package com.kedacom.keda.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kedacom.category.model.Category;
import com.kedacom.keda.service.CategoryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/introduction/{id}")
	@HystrixCommand(fallbackMethod = "homeFallback")
	public String home(Map<String, Object> model, @PathVariable long id) {
		Category category = categoryService.getCategory(id);

		model.put("category", category);

		return "introduction";
	}

	// 熔断处理
	public String homeFallback(Map<String, Object> model, @PathVariable long id) {

		return "访问人太多，请稍等";
	}
}
