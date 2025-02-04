package com.example.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderLine implements Serializable {
    String lineId;
    Integer orderedQty;
    String unitPrice;
    String sourceChannel;

//    public String getLineId() {
//        return lineId;
//    }
//
//    public void setLineId(String lineId) {
//        this.lineId = lineId;
//    }
//
//    public int getOrderedQty() {
//        return orderedQty;
//    }
//
//    public void setOrderedQty(int orderedQty) {
//        this.orderedQty = orderedQty;
//    }
//
//    public String getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(String unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public String getSourceChannel() {
//        return sourceChannel;
//    }
//
//    public void setSourceChannel(String sourceChannel) {
//        this.sourceChannel = sourceChannel;
//    }
}
