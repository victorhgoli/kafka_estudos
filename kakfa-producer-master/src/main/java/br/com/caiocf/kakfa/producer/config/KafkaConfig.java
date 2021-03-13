package br.com.caiocf.kakfa.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.caiocf.kakfa.model.User;


@EnableKafka
@Configuration
public class KafkaConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Bean
	public ProducerFactory<String, String> producerFactory(){
		
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configProps);
	}
	
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
	
	
	
	
	@Bean
	public ProducerFactory<String, User> producerUserFactory(){
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, User>(configProps);
	}
	
	@Bean(name = "kafkaUserTemplate")
	public KafkaTemplate<String, User> kafkaUserTemplate() {
        return new KafkaTemplate<>(producerUserFactory());
    }
	
}
