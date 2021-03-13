package br.com.caiocf.kakfa.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caiocf.kakfa.model.User;

@RestController
public class UserController {

	@Autowired
	@Qualifier("kafkaTemplate")
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	@Qualifier("kafkaUserTemplate")
	private KafkaTemplate<String, User> kafkaUserTemplate;
	
	private static final String TOPIC = "Kafka_Message";
	private static final String TOPICUSER = "Kafka_User";
	
	
	@GetMapping("/publish/{message}")
	public 	String post(@PathVariable("message") final String message) {
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message=[" + message + 
			              "] with offset=[" + result.getRecordMetadata().offset() + "]");
				//result = "OK";
			}

			@Override
			public void onFailure(Throwable ex) {
				 System.out.println("Unable to send message=["
			              + message + "] due to : " + ex.getMessage());
				 //result = "Failed";
			}
		});
		
		return "OK";
	}
	
	
	@GetMapping("/publish/user/{name}/{year}")
	public 	String postUser(@PathVariable("name") final String name, @PathVariable("year") final Integer year) {
		
		User user = new User(name,year);
		
		ListenableFuture<SendResult<String, User>> future = kafkaUserTemplate.send(TOPICUSER, user);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
			@Override
			public void onSuccess(SendResult<String, User> result) {
				System.out.println("Sent message=[" + user + 
			              "] with offset=[" + result.getRecordMetadata().offset() + "]");
				//result = "OK";
			}

			@Override
			public void onFailure(Throwable ex) {
				 System.out.println("Unable to send message=["
			              + user + "] due to : " + ex.getMessage());
				 //result = "Failed";
			}
		});
		
		return "OK";
	}
}
