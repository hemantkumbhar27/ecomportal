package com.example.ecomorder.entity;

import com.google.cloud.Timestamp;
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
@Table(name = "Bag")
public class Bag {

    @PrimaryKey
    @Column(name = "bagguid")
    private String bagGuid;  // Unique identifier for the bag (primary key)

    @Column(name = "userguid")
    private String userGuid;  // Foreign key reference to User

    @Column(name = "userid")
    private BigDecimal userId;  // User-specific identifier (NUMERIC type)

    @Column(name = "created")
    private Timestamp created;  // Creation time (Spanner TIMESTAMP)

    @Column(name = "updated")
    private Timestamp updated;  // Last update time (Spanner TIMESTAMP)

    @Column(name = "bagid")
    private String bagId;  // Unique Bag ID
}
