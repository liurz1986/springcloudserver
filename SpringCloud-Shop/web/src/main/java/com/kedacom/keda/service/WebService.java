package com.kedacom.keda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kedacom.category.model.Category;
import com.kedacom.user.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Ribbon+RestTemplate
 * 增加了熔断处理 @HystrixCommand注解,注解中增加fallbackMethod定义熔断返回的方法,熔断方法就定义该类里面
 * 
 * @ClassName: WebService
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月26日 下午9:50:47
 *
 */
@Service
public class WebService {
	private final Logger logger = LoggerFactory.getLogger(WebService.class);

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getCategoryFallback")
	public Category getCategory(Long id) {
		// System.out.println("getCategory");
		return restTemplate.getForEntity("http://CATEGORY-SERVICE/category/" + id, Category.class).getBody();
	}

	private Category getCategoryFallback(Long id) {
		// throw new ServiceUnAvailableException("CATEGORY-SERVICE");
		logger.error("CATEGORY-SERVICE unavailable");
		return new Category();
	}

	@HystrixCommand(fallbackMethod = "getByNameFallback")
	public User getByName(String name) {
		return restTemplate.getForEntity("http://USER-SERVICE/user/getByName/" + name, User.class).getBody();
	}

	private User getByNameFallback(String name) {
		logger.error("CAROUSEL-SERVICE unavailable");
		return new User();
	}

	@HystrixCommand(fallbackMethod = "saveUserFallback")
	public User saveUser(User user) {
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://USER-SERVICE/category", User.class,
				null);
		return responseEntity.getBody();
	}

	private User saveUserFallback(User user) {
		logger.error("insertUser unavailable");
		return new User();
	}

	// addOrder
}
