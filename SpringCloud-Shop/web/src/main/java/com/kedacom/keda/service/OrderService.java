package com.kedacom.keda.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kedacom.keda.common.OrderVo;
import com.kedacom.keda.fallback.OrderServiceFallBack;

/**
 * Feign 增加了熔断处理：熔断处理类OrderServiceFallBack, 需要实现@FeignClient所在接口,
 * 并实现接口中的方法,该方法熔断后处理方法
 * 
 * @ClassName: OrderService
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月26日 下午9:44:47
 *
 */
@FeignClient(value = "order-service", fallback = OrderServiceFallBack.class)
public interface OrderService {

	@PostMapping("/order/addOrder")
	boolean addOrder(@RequestBody OrderVo orderVo);

}
