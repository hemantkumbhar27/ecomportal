package com.example.config;

import lombok.Data;

@Data
public class ReplicationConfig {

    String subscriptionName;

    String topicName;

    String handleDuplicateFailure;

}
