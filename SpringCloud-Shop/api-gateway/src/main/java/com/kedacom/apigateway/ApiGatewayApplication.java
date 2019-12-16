package com.kedacom.apigateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.kedacom.apigateway.filter.AccessFilter;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@EnableZuulProxy
@SpringCloudApplication
@EnableFeignClients // 开启扫描Spring Cloud Feign客户端的功能
public class ApiGatewayApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run(args);
	}

	// 过滤器配置
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	// 自定义负载均衡策列--全局
	@Bean
	public IRule myRule() {
		/* RandomRule为Ribbon中自带规则实现之一，随机规则 */
		return new RandomRule();
	}

}
