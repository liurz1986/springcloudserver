package com.kedacom.keda.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedacom.keda.common.Result;
import com.kedacom.keda.common.ResultUtil;
import com.kedacom.keda.exception.MyException;

//统一异常处理
//@ControllerAdvice
public class ExceptionHandle {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result handle(Exception e) {
		if (e instanceof MyException) {
			MyException myException = (MyException) e;
			return ResultUtil.error(myException.getCode(), myException.getMessage());
		} else {
			logger.error("系统异常={}", e);
			return ResultUtil.error(-1, "未知错误");
		}
	}
}
