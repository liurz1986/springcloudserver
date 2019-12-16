package com.kedacom.keda.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kedacom.category.model.Category;
import com.kedacom.keda.fallback.CategoryServiceFallBack;

/**
 * Feign 增加了熔断处理：熔断处理类CategoryServiceFallBack, 需要实现@FeignClient所在接口,
 * 并实现接口中的方法,该方法熔断后处理方法
 * 
 * @ClassName: CategoryService
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月26日 下午9:48:37
 *
 */
@FeignClient(value = "category-service", fallback = CategoryServiceFallBack.class)
public interface CategoryService {

	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable("id") Long id);

}
