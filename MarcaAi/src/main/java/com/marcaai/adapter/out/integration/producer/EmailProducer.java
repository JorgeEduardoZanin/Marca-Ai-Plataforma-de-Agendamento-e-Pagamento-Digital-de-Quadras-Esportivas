package com.marcaai.adapter.out.integration.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marcaai.adapter.out.integration.dto.request.VerificationMail;


@Component
public class EmailProducer {

	private final String directExchange;

    private final String emailRoutingKey;
	
	private final RabbitTemplate rabbitTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailProducer.class);
	
	public EmailProducer(
			@Value("${rabbitmq.exchange.mail}") String directExchange,
			@Value("${rabbitmq.route.key.mail}") String emailRoutingKey,
			RabbitTemplate rabbitTemplate) {
		
		this.directExchange = directExchange;
		this.emailRoutingKey = emailRoutingKey;
		this.rabbitTemplate = rabbitTemplate;
	}


	public void sendEmailVerification(String to, String code) {
		var request = new VerificationMail(to, code);
		rabbitTemplate.convertAndSend(directExchange, emailRoutingKey, request);
		logger.info("Exchange: "+directExchange+" RoutingKey: "+emailRoutingKey+ " Request: "+request);
	}
	
}
