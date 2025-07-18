package com.marcaai.emails.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
	
	 @Bean
	 public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		 return new RabbitAdmin(connectionFactory);
	 }

	 @Bean
	 public MessageConverter messageConverter() {
		 return new Jackson2JsonMessageConverter();
	 }
	 
	 @Bean
	 @Primary
	 public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		 var rabbitTemplate = new RabbitTemplate(connectionFactory);
		 rabbitTemplate.setMessageConverter(messageConverter);
		 return rabbitTemplate;
	 }
	 
	 @Bean
	 public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin rabbitAdmin){
		 return event -> rabbitAdmin.initialize();
	 }
	
}
