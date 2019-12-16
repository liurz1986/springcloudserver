package com.kedacom.keda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedacom.keda.common.OrderVo;
import com.kedacom.keda.common.Result;
import com.kedacom.keda.common.ResultUtil;
import com.kedacom.keda.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/addOrder")
	@HystrixCommand(fallbackMethod = "addOrderFallback")
	public Result addOrder(OrderVo orderVo, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return ResultUtil.error(3, "你还未登录");
		} else {
			orderVo.setUserId(userId);
			orderService.addOrder(orderVo);
			return ResultUtil.success();
		}
	}

	/**
	 * 熔断处理方法
	 * 
	 * @Title: addOrderFallback
	 * @Description: TODO
	 * @param orderVo
	 * @param session
	 *            熔断后：记录容器信息
	 */
	public Result addOrderFallback(OrderVo orderVo, HttpSession session) {

		return ResultUtil.error(3, "你还未登录");
	}
}
