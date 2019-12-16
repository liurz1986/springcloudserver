package com.kedacom.keda.fallback;

import org.springframework.stereotype.Component;

import com.kedacom.category.model.Category;
import com.kedacom.keda.service.CategoryService;

@Component
public class CategoryServiceFallBack implements CategoryService {

	@Override
	public Category getCategory(Long id) {
		System.out.println("调用getCategory接口失败");
		return null;
	}

}
