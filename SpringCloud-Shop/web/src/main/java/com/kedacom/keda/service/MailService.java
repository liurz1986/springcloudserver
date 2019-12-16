package com.kedacom.keda.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kedacom.keda.common.MailUtil;

/**
 * 异常处理 1.发送邮件
 * 
 * @ClassName: MailService
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年4月27日 上午2:00:59
 *
 */
@Service
public class MailService {
	@Autowired
	private RedisTemplate<String, String> redis;

	@Autowired
	private JavaMailSender mailSender;

	public void handle(String key, String interfaceName, String errorMessage) {
		// 获取上次发邮件的时间，用缓存存储发邮件的时间
		// key存在
		if (redis.hasKey(key)) {
			String value = redis.opsForValue().get(key);
			if (StringUtils.isNotEmpty(value)) {
				SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				try {
					Date oldTime = data.parse(value);
					Date nowTime = new Date();
					if (oldTime.before(nowTime)) {
						long cha = nowTime.getTime() - oldTime.getTime();
						// 大于5分钟，发邮件
						if (cha > 300000) {
							// 异步发邮件
							ExecutorService pool = Executors.newFixedThreadPool(10);
							pool.execute(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									sendMail(interfaceName, errorMessage);
								}

							});

						}
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		// 发邮件给服务处理责任人
	}

	public void sendMail(String interfaceName, String errorMessage) {
		String mailContext = errorMessage;
		String from = "dyc87112@qq.com";
		String[] to = { "dyc87112@qq.com" };
		String subject = interfaceName + "接口调用异常请及时处理";

		MailUtil.sendMail(mailSender, mailContext, from, to, subject);
	}
}
