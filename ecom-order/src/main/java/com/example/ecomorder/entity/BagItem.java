package com.example.ecomorder.entity;

import com.google.cloud.Timestamp;
import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bag_Item")
public class BagItem {

    public String getBagGuid() {
        return bagGuid;
    }

    public void setBagGuid(String bagGuid) {
        this.bagGuid = bagGuid;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setBagId(String bagId) {
        this.bagId = bagId;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public String getItemId() {
        return itemId;
    }

    public String getBagId() {
        return bagId;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    @PrimaryKey
    @Column(name = "bagguid")
    private String bagGuid;  // Foreign key reference to Bag

    @PrimaryKey
    @Column(name = "itemid")
    private String itemId;  // Foreign key reference to Item

    @Column(name = "bagid")
    private String bagId;  // Bag ID

    @Column(name = "added_at")
    private Timestamp addedAt;  // Timestamp when the item was added (Spanner TIMESTAMP)
}