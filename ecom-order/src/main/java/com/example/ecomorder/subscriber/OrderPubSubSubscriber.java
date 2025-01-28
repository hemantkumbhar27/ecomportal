package com.example.ecomorder.subscriber;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
public class OrderPubSubSubscriber {
    @Value("${order.message.subscription.name}")
    String subscription;

    @Autowired
    @Qualifier("orderMessageChannel")
    MessageChannel messageChannel;

    @Autowired
    @Qualifier("orderSubTemplate")
    PubSubTemplate template;
    @Bean
    public PubSubInboundChannelAdapter pubSubInboundChannelAdapter(){
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template, subscription);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setOutputChannel(messageChannel);
        adapter.setPayloadType(String.class);
        return adapter;
    }

    @ServiceActivator(inputChannel = "orderMessageChannel")
    public void orderMessageReceiver(String payload, @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE)BasicAcknowledgeablePubsubMessage message){
        System.out.println("Received Order to ack and process message");
        System.out.println("Payload : : "+ payload);
        message.ack();
        System.out.println("Acknowledged message id :: " + message.getPubsubMessage().getMessageId());
    }
}
