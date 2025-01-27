package com.example.ecomorder.publisher;

import com.example.ecomorder.model.OrderCollect;
import com.example.ecomorder.model.OrderLine;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
@ConditionalOnProperty(name = "default.order.message.publish.enabled", havingValue = "true")
@Component
public class OrderPublisher {
    @Autowired
    @Qualifier("orderPubSubTemplate")
    PubSubTemplate pubSubTemplate;
    @Value("${order.message.publisher.topicId}")
    String topic;
    @PostConstruct
    public void init() {
        var orderCollect = new OrderCollect();
        var orderLine = new OrderLine();
        orderLine.setLineId("LINE1234");
        orderLine.setOrderedQty(10);
        orderLine.setUnitPrice("100");
        orderCollect.setOrderNumber("123456789");
        orderCollect.setOrderLine(List.of(orderLine));

        pubSubTemplate.publish(topic, orderCollect).whenComplete((response, error)->{
            if(error!=null){
                System.out.println("Ooops something went wrong : " + error.getMessage());
            }else {
                System.out.println("published message successfully : " + response);
            }
        });
    }
}
