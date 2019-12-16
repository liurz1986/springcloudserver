package com.kedacom.keda.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kedacom.keda.fallback.UserServiceFallback;
import com.kedacom.user.model.User;

/**
 * Feign 增加了熔断处理：熔断处理类UserServiceFallback, 需要实现@FeignClient所在接口,
 * 并实现接口中的方法,该方法熔断后处理方法
 * 
 * @ClassName: UserService
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月26日 下午9:50:15
 *
 */
@FeignClient(name = "user-service", fallback = UserServiceFallback.class)
public interface UserService {

	@PostMapping("/user/login")
	User login(@RequestBody User user);

	@PostMapping("user/insertUser")
	Boolean register(@RequestBody User user);

}
