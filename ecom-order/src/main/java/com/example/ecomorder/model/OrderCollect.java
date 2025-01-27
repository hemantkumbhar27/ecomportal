package com.example.ecomorder.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCollect {
    String orderNumber;
    List<OrderLine> orderLines;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLine> getOrderLine() {
        return orderLines;
    }

    public void setOrderLine(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
