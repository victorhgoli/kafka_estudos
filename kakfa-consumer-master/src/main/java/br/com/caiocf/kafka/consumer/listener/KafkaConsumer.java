package br.com.caiocf.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.caiocf.kakfa.model.User;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "Kafka_Message", groupId = "group_string" )
	public void consume(String message) {
        System.out.println("Consumed message: " + message);
	}
	
	@KafkaListener(topics = "Kafka_User", groupId = "group_json", containerFactory = "userKafkaListenerFactory" )
	public void consumeUser(User user) {
        System.out.println("Consumed Json user: " + user);
	}
}
