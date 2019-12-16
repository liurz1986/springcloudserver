package com.kedacom.order.service;

import com.kedacom.order.common.OrderVo;
import com.kedacom.order.model.Order;

public interface OrderService {
	   
	boolean save(OrderVo orderVo);
}
