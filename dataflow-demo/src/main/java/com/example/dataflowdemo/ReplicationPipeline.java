package com.example.dataflowdemo;

import com.example.EcomPipelineOptions;
import com.example.enums.PipelineEnum;

import com.example.fn.ConvertMsgToOrderFn;
import com.example.fn.ReplicationMutationFn;
import com.example.model.OrderLine;
import org.apache.beam.sdk.io.gcp.spanner.MutationGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;

import org.apache.beam.sdk.io.gcp.spanner.SpannerConfig;
import org.apache.beam.sdk.io.gcp.spanner.SpannerIO;
import org.apache.beam.sdk.io.gcp.spanner.SpannerWriteResult;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReplicationPipeline {

    private static final String READ_REPLICATION_MESSAGE = "Read replication message";

    private static final String CONVERT_MSG_TO_ORDERLINE = "Convert msg to orderline";

    private static final String WRITE_TO_SPANNER = "WRITE_TO_SPANNER";

    private static final String CREATE_MUTATION_GROUP="CREATE_MUTATION_GROUP";

    @Autowired
    ConvertMsgToOrderFn convertMsgtoOrderFn;

    @Autowired
    ReplicationMutationFn replicationMutationFn;

    public void runPipeline(EcomPipelineOptions options, PipelineEnum pipelineEnum) {

        Pipeline pipeline= Pipeline.create(options);

        //Read replication message from pubsub
        PCollection<String> pubsubData=null;

        if(pipelineEnum.isReplication()){
            pubsubData=pipeline.apply(READ_REPLICATION_MESSAGE, PubsubIO.readStrings().fromSubscription(options.getSubscriptionName()));
        }

        //Convert msg to obj
        PCollection<OrderLine> replicationObj = pubsubData.apply(CONVERT_MSG_TO_ORDERLINE, ParDo.of(convertMsgtoOrderFn));


        //Create Mutation Group
        PCollection<MutationGroup> mutationCollection=replicationObj.apply(CREATE_MUTATION_GROUP,ParDo.of(replicationMutationFn));

        SpannerConfig   spannerConfig = SpannerConfig.create()
                .withProjectId(options.getProject())
                .withDatabaseId(options.getSpannerDBId())
                .withInstanceId(options.getSpannerInstanceId());

        //Writing to Spanner
        SpannerWriteResult  spannerWriteResult=mutationCollection.apply(
                WRITE_TO_SPANNER,
                SpannerIO.write().withSpannerConfig(spannerConfig)
                        .withBatchSizeBytes(0)
                        .withFailureMode(SpannerIO.FailureMode.valueOf(options.getSpannerFailureMode())).grouped());

//        SpannerWriteStatusHandler.createFailedStatus(spannerWriteResult, options, spannerConfig);

        pipeline.run();


    }

}
