package com.example.ecomorder.subscriber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelIntegrationConfig {

    @Bean
    public MessageChannel orderMessageChannel(){
        return new PublishSubscribeChannel();
    }
}
