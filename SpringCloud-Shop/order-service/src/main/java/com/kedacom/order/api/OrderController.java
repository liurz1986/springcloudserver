package com.kedacom.order.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedacom.order.common.OrderVo;

@RestController
@RequestMapping("/order")
public class OrderController {

	// @Autowired
	// private OrderService orderService;

	// @Autowired
	// DiscoveryClient discoveryClient;

	@PostMapping("/addOrder")
	public Map<String, Object> addOrder(@RequestBody OrderVo orderVo) {
		// orderService.save(orderVo);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("data", "messsage");
		param.put("code", 0);
		return param;
	}
}
