package com.example.replication;

import com.example.EcomPipelineOptions;
import com.example.model.OrderLine;
import com.google.cloud.spanner.DatabaseClient;
import org.apache.beam.sdk.io.gcp.spanner.MutationGroup;
import org.apache.avro.Schema;

public class OrderLineMutation {

    DatabaseClient dbClient;
    EcomPipelineOptions options;
    public OrderLineMutation(DatabaseClient dbClient, EcomPipelineOptions options) {
        this.dbClient = dbClient;
        this.options = options;
    }


    public MutationGroup createMutationGroup(OrderLine orderLine){
        MutationGroup mutationGroup = null;

        return  mutationGroup;
    }
}
