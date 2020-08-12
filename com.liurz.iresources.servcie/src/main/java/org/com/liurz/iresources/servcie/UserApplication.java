package org.com.liurz.iresources.servcie;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.springframework.cloud.context.config.annotation.RefreshScope;

//@EnableEurekaClient
@SpringBootApplication
//@RefreshScope // 配置文件更新配置，可以手动刷新配置
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	/**
	 * 使用阿里的json替换默认的jackson，进行http请求和响应数据json控制
	 * 
	 * @Title: fastJsonConfigure
	 * @Description: TODO
	 * @return
	 * @return HttpMessageConverters
	 */
	// @Bean
	public HttpMessageConverters fastJsonConfigure() {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		// 日期格式化 可以避免java中date返回到前端转化成时间戳
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.BrowserCompatible,
				SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
		converter.setFastJsonConfig(fastJsonConfig);
		List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
		// 数据格式为json
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(fastMediaTypes);
		return new HttpMessageConverters(converter);
	}

	/**
	 * 设置jackson，进行http请求和响应数据json控制,主要是设置时间格式---默认就是jackson
	 * 
	 * 
	 * @Title: jackson
	 * @Description: TODO
	 * @return
	 * @return HttpMessageConverters
	 */
	// @Bean
	public HttpMessageConverters jackJsonConfigure() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		// 日期格式化 可以避免java中date返回到前端转化成时间戳
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(dateFormat);
		converter.setObjectMapper(objectMapper);
		List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
		// 数据格式为json
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(fastMediaTypes);
		return new HttpMessageConverters(converter);
	}

	// 验证git上配置更新时
	@Value("${message:Hello default}")
	private String message;

	// https://localhost:8080/usercenter/demo
	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public Map<String, String> test2() {
		Map<String, String> params = new HashMap<String, String>();// 线程不安全的
		params.put("data", message);
		params.put("status" ,"success");
		return params;
	}

}
