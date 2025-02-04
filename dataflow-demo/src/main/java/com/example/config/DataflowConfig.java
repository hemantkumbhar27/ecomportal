package com.example.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
@Data
@ConfigurationProperties(prefix="dataflow")
public class DataflowConfig {
    String gcpProject;
    String network;
    String subnetwork;
    String serviceAccount;
    String region;
    String stagingBucketLocation;
    String workZone;
    boolean usePublicIPs;
    int numberWorkers;
    int maxNumWorkers;
    String tempLocation;
    String spannerProjectId;
    String spannerInstanceId;
    String spannerDatabaseName;
    String spannerDatasetId;
    String spannerTableId;
    String inputFileName;
    String outputFileName;

    String inputCSVBucketLocation;

    String outputCSVBucketLocation;

    Long batchSizeInBytes;

    String workerMachineType;

    Integer workerCacheMb;

    Map<String, String> loggerMap;

    ReplicationConfig replication;

    MigrationConfig migration;

    String spannerFailureMode;

    int minSpannerSessions;

    int maxSpannerSessions;

    String gcpTempLocation;




}