package com.rabbitmq.rabbitmqdemo;

import com.rabbitmq.rabbitmqdemo.common.SpringContextUtil;
import com.rabbitmq.rabbitmqdemo.message.handler.MessageNotifier;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@ImportResource(value = {
		"classpath:META-INF/rabbitmq/*.xml"
})
@EnableAsync
public class RabbitmqDemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =  SpringApplication.run(RabbitmqDemoApplication.class, args);
		SpringContextUtil.setApplicationContext(applicationContext);

	}
}
