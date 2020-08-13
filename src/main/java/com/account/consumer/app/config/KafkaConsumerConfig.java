package com.account.consumer.app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import com.account.consumer.app.entity.Account;
import com.account.consumer.app.exception.AccountExsistedException;
import com.account.consumer.app.service.AccountService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {
	
	private final AccountService accountService;
	@Value("${kafka-topic}")
	private String topic;
	
	@Bean
    public ConsumerFactory<String, String> consumerFatory() {
    	
        Map<String, Object> config = new HashMap<String ,Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroupId");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        
        return new DefaultKafkaConsumerFactory<String,String>(config);
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    	
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFatory());
        concurrentKafkaListenerContainerFactory.setMissingTopicsFatal(false);
        
        return concurrentKafkaListenerContainerFactory;
    }
 
    @Bean
    public Gson gsonJsonConverter() {
        return new Gson();
    }
    
    @KafkaListener(topics= {"Test4"})
    public void getTopics(@RequestBody String account) throws AccountExsistedException {
    	
        log.info("Kafka event consumed is: " + account+" topic "+topic);
       accountService.addAccount(gsonJsonConverter().fromJson(account, Account.class));
    }
}
