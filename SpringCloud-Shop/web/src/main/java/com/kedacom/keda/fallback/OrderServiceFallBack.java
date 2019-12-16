package com.kedacom.keda.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kedacom.keda.service.MailService;
import com.kedacom.keda.service.OrderService;

/**
 * OrderService的熔断处理
 * 
 * @ClassName: OrderServiceFallBack
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月26日 下午9:42:15
 *
 *       发邮件给服务对应处理者，请即使处理 发邮件频次：五分钟只发一次，避免频繁发邮件
 */
@Component
public class OrderServiceFallBack implements OrderService {

	private static String OrderServiceFallBack_key = "cn.com.kedacom.keda.OrderServiceFallBack.rediskey";
	@Autowired
	private MailService mailService;

	@Override
	public boolean addOrder(com.kedacom.keda.common.OrderVo orderVo) {
		mailService.handle(OrderServiceFallBack_key, OrderService.class.getName(), "调用接口超时");
		return false;
	}

}
