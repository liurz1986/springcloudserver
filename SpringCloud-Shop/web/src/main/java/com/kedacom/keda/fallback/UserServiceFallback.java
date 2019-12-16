package com.kedacom.keda.fallback;

import org.springframework.stereotype.Component;

import com.kedacom.keda.service.UserService;
import com.kedacom.user.model.User;

/**
 * 熔断后降级处理类
 * 
 * @ClassName: UserServiceFallback
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月27日 上午12:04:18
 *
 */
@Component
public class UserServiceFallback implements UserService {
	@Override
	public User login(User user) {
		return null;
	}

	@Override
	public Boolean register(User user) {
		return null;
	}

}
