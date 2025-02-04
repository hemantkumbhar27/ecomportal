package com.example.fn;

import com.example.EcomPipelineOptions;
import com.example.model.OrderLine;
import com.example.replication.OrderLineMutation;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.SessionPoolOptions;
import com.google.cloud.spanner.Spanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.io.gcp.spanner.MutationGroup;
import org.apache.beam.sdk.transforms.DoFn;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReplicationMutationFn extends DoFn<OrderLine, MutationGroup> {

    Spanner spanner;
    DatabaseClient dbClient;

    @StartBundle
    public void startBundle(StartBundleContext c) {
        EcomPipelineOptions options = c.getPipelineOptions().as(EcomPipelineOptions.class);
        SessionPoolOptions sessionPoolOptions=SessionPoolOptions.newBuilder()
                .setMaxSessions(options.getMaxSpannerSessions())
                .setMinSessions( options.getMinSpannerSessions())
                .setWarnAndCloseIfInactiveTransactions()
                .build();

        com.google.cloud.spanner.SpannerOptions spannerOptions = com.google.cloud.spanner.SpannerOptions
                .newBuilder()
                .setSessionPoolOption(sessionPoolOptions)
                .build();

        spanner = spannerOptions.getService();
        String spannerProjectID = options.getSpannerProjectId();
        String spannerInstanceID = options.getSpannerInstanceId();
        String spannerDatabaseID = options.getSpannerDBId();

        DatabaseId db = DatabaseId.of(spannerProjectID, spannerInstanceID, spannerDatabaseID);
        dbClient=spanner.getDatabaseClient(db);

    }

    @ProcessElement
    public void processElement(ProcessContext c) {
        EcomPipelineOptions ecomPipelineOptions = c.getPipelineOptions().as(EcomPipelineOptions.class);
        OrderLine orderLine = c.element();
        MutationGroup mutationGroup =null;

        mutationGroup=createOrderLineMutation(orderLine, ecomPipelineOptions);

    }

    public MutationGroup createOrderLineMutation(OrderLine orderLine, EcomPipelineOptions options) {
        try {
            return new OrderLineMutation(dbClient,options).createMutationGroup(orderLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FinishBundle
    public void finishBundle(FinishBundleContext c) {spanner.close();}
}
