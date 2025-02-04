package com.example;

import com.example.config.DataflowConfig;
import com.example.dataflowdemo.CommandLineOptions;
import com.example.dataflowdemo.ReplicationPipeline;
import com.example.enums.PipelineEnum;
import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.SdkHarnessOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.Serializable;

@SpringBootApplication
public class DataflowDemoApplication implements CommandLineRunner, Serializable {

    @Autowired
    private final Environment environment;

    @Autowired
    DataflowConfig dataflowConfig;

    @Autowired
    private ReplicationPipeline replicationPipeline;

    public DataflowDemoApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataflowDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PipelineEnum pipelineEnum = PipelineEnum.valueOf(environment.getProperty("flow"));
        EcomPipelineOptions EcomPipelineOptions = createPipelineOptions(pipelineEnum);

        replicationPipeline.runPipeline(EcomPipelineOptions,pipelineEnum );


    }
    private EcomPipelineOptions createPipelineOptions(PipelineEnum pipelineEnum) {
        PipelineOptionsFactory.register(EcomPipelineOptions.class);
        EcomPipelineOptions options=PipelineOptionsFactory.create().as(EcomPipelineOptions.class);

        options.setPipelineEnum(pipelineEnum);

        options.setProject(dataflowConfig.getGcpProject());
        options.setRegion(dataflowConfig.getRegion());
        options.setServiceAccount(dataflowConfig.getServiceAccount());
        options.setNetwork(dataflowConfig.getNetwork());
        options.setSubnetwork(dataflowConfig.getSubnetwork());
        options.setWorkerZone(dataflowConfig.getWorkZone());
        options.setUsePublicIps(dataflowConfig.isUsePublicIPs());
        options.setNetwork(dataflowConfig.getNetwork());
        options.setMaxNumWorkers(dataflowConfig.getMaxNumWorkers());
        options.setStreaming(true);
        options.setNumWorkers(dataflowConfig.getNumberWorkers());
        options.setMaxNumWorkers(dataflowConfig.getMaxNumWorkers());
        options.setWorkerMachineType(dataflowConfig.getWorkerMachineType());
        options.setRunner(DataflowRunner.class);
        options.setSpannerDBId(dataflowConfig.getSpannerDatasetId());
        options.setSpannerInstanceId(dataflowConfig.getSpannerInstanceId());
        options.setSpannerProjectId(dataflowConfig.getSpannerProjectId());
        options.setSpannerFailureMode(dataflowConfig.getSpannerFailureMode());
        options.setMinSpannerSessions(dataflowConfig.getMinSpannerSessions());
        options.setMaxSpannerSessions(dataflowConfig.getMaxSpannerSessions());
        options.setTopicName(dataflowConfig.getReplication().getTopicName());
        options.setSubscriptionName(dataflowConfig.getReplication().getSubscriptionName());
        options.setGcpTempLocation(dataflowConfig.getGcpTempLocation());

        options.setDefaultSdkHarnessLogLevel(SdkHarnessOptions.LogLevel.WARN);

        options.setSdkHarnessLogLevelOverrides(SdkHarnessOptions.SdkHarnessLogLevelOverrides.from(dataflowConfig.getLoggerMap()));

        if (pipelineEnum.isReplication()){
            options.setHandleDuplicateFailure(dataflowConfig.getReplication().getHandleDuplicateFailure());
        }

        CommandLineOptions.updatePipelineOptions(options,environment);
        return options;
    }
}
