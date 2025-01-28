package com.example.ecomorder.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderPublishConfig extends PubSubBaseConfig{
    @Value("${order.message.publisher.gcpProjectId}")
    String projectId;

    public String getConfigureProjectId(){
        return  this.projectId;
    }

    @Bean("orderPubSubTemplate")
    public PubSubTemplate orderPubSubTemplate(){
        PubSubTemplate pubSubTemplate = new PubSubTemplate(createPublisherFactory(), createSubscriberFactory());
        pubSubTemplate.setMessageConverter(createJacksonPubSubMessageConverter());
        return pubSubTemplate;
    }
    @Bean("orderSubTemplate")
    public PubSubTemplate orderSubTemplate(){
        return new PubSubTemplate(createPublisherFactory(), createSubscriberFactory());
    }
}
