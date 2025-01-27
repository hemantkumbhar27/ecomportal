package com.example.ecomorder.entity;


import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Item")
public class Item {

    @PrimaryKey
    @Column(name = "itemid")
    private String itemId;  // Unique identifier for the item

    @Column(name = "itemname")
    private String itemName;  // Name of the item

    @Column(name = "description")
    private String description;  // Description of the item

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "category")
    private String category;  // Category of the item

    @Column(name = "unitprice")
    private BigDecimal unitPrice;  // Price per unit

    @Column(name = "status")
    private String status = "active";  // Status of the item (default: "active")

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getStatus() {
        return status;
    }
}
