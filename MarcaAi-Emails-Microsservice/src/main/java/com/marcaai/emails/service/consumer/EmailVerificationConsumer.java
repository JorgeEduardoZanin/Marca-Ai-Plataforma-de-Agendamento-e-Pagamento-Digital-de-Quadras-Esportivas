package com.marcaai.emails.service.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.marcaai.emails.dto.response.EmailVerificationResponse;
import com.marcaai.emails.service.GmailService;
import com.rabbitmq.client.Channel;




@Component
public class EmailVerificationConsumer {
	
	private final GmailService gmailService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailVerificationConsumer.class);
	
	public EmailVerificationConsumer(GmailService gmailService) {
		this.gmailService = gmailService;
	}


	@RabbitListener(bindings = @QueueBinding (
	        value = @Queue(
	            name = "${rabbitmq.queue.mail.verification}", 
	            durable = "true"
	        ),
	        exchange = @Exchange(
	            name = "${rabbitmq.exchange.mail.verification}", 
	            type = ExchangeTypes.DIRECT, 
	            durable = "true"
	        ),
	        key = "${rabbitmq.route.key.mail.verification}"
	    ),
			ackMode = "MANUAL")
	public void sendEmailVerification(EmailVerificationResponse response, Message message, Channel channel) throws IOException {
			
		
			logger.info(response.toString());
			gmailService.sendEmail(response.to(), response.code());
			
			/*if(!sendMail) {
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}else {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
			}
		} catch (Exception e) {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}*/
	}
	
}
