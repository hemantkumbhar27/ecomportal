package com.example.ecomorder.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.api.gax.retrying.RetrySettings;
import com.google.cloud.spring.pubsub.core.PubSubConfiguration;
import com.google.cloud.spring.pubsub.support.*;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;

import java.time.Duration;


public class PubSubBaseConfig{
    Duration initialRetryDelay = Duration.ofMillis(100);
    double retryDelayMultiplier= 2.0;
    Duration maxRetryDelay = Duration.ofSeconds(60);
    Duration initialRpcTimeout = Duration.ofSeconds(10);
    double rpcTimeoutMultiplier = 1.0;
    Duration maxRpcTimeout = Duration.ofSeconds(600);
    Duration totalTimeout = Duration.ofSeconds(600);
    int maxAttempts = 3;

    public PublisherFactory createPublisherFactory(){
        DefaultPublisherFactory defaultPublisherFactory = new DefaultPublisherFactory(this::getConfigureProjectId);
        defaultPublisherFactory.setRetrySettings(createRetrySettings());
        return new CachingPublisherFactory(defaultPublisherFactory);
    }
    public SubscriberFactory createSubscriberFactory(){
        PubSubConfiguration pubSubConfiguration = new PubSubConfiguration();
        pubSubConfiguration.initialize(getConfigureProjectId());
        return new DefaultSubscriberFactory(this::getConfigureProjectId, pubSubConfiguration);
    }
    public PubSubMessageConverter createJacksonPubSubMessageConverter(){
        return new JacksonPubSubMessageConverter(new ObjectMapper());
    }
    public PubSubMessageConverter createJacksonPubSubXMLMessageConverter(){
        return new JacksonPubSubMessageConverter(new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    private RetrySettings createRetrySettings(){
        return RetrySettings.newBuilder()
                .setMaxAttempts(maxAttempts)
                .setInitialRetryDelayDuration(initialRetryDelay)
                .setRetryDelayMultiplier(retryDelayMultiplier)
                .setMaxRetryDelayDuration(maxRetryDelay)
                .setInitialRpcTimeoutDuration(initialRpcTimeout)
                .setRpcTimeoutMultiplier(rpcTimeoutMultiplier)
                .setMaxRpcTimeoutDuration(maxRpcTimeout)
                .setTotalTimeoutDuration(totalTimeout)
                .build();
    }

    protected String getConfigureProjectId(){
        return null;
    }
}