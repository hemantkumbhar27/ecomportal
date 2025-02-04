package com.example.fn;

import com.example.model.OrderLine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.transforms.DoFn;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConvertMsgToOrderFn extends DoFn<String, OrderLine> {

    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @ProcessElement
    public void processElement(ProcessContext context) {
        String replicationMsg = context.element();
        System.err.println("Replication Message: " + replicationMsg);
        try {
            context.output(objectMapper.readValue(replicationMsg, OrderLine.class));
        } catch (JsonProcessingException e) {
            System.err.println("Failed to convert message: " + e);
        }
    }
}
