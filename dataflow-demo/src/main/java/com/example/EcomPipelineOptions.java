package com.example;

import com.example.enums.PipelineEnum;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.SdkHarnessOptions;

public interface EcomPipelineOptions extends DataflowPipelineOptions, GcpOptions {
    void setSpannerInstanceId(String instanceId);
    String getSpannerInstanceId();

    void setSpannerDBId(String dbId);
    String getSpannerDBId();

    void setSpannerProjectId(String projectId);
    String getSpannerProjectId();

    void setMinSpannerSessions(Integer minSpannerSessions);
    Integer getMinSpannerSessions();

    void setMaxSpannerSessions(Integer maxSpannerSessions);
    Integer getMaxSpannerSessions();

    void setTopicName(String topicName);
    String getTopicName();

    void setSubscriptionName(String subscriptionName);
    String getSubscriptionName();

    void setPipelineEnum(PipelineEnum pipelineEnum);
    PipelineEnum getPipelineEnum();

    void setBatchSize(int batchSize);
    int getBatchSize();

    void setSpannerFailureMode(String failureMode);
    String getSpannerFailureMode();

    void setBatchSizeInBytes(Long batchSizeInBytes);
    Long getBatchSizeInBytes();


    @Default.Enum("INFO")  // Ensure default is set
    SdkHarnessOptions.LogLevel getDefaultSdkHarnessLogLevel();
    void setDefaultSdkHarnessLogLevel(SdkHarnessOptions.LogLevel defaultSdkHarnessLogLevel);


    void setSdkHarnessLogLevelOverrides(SdkHarnessOptions.SdkHarnessLogLevelOverrides sdkHarnessLogLevelOverrides);
    SdkHarnessOptions.SdkHarnessLogLevelOverrides getSdkHarnessLogLevelOverrides();

    void setHandleDuplicateFailure(String handleDuplicateFailure);
    String getHandleDuplicateFailure();

//
//    void setGcpTempLocation(String gcpTempLocation);
//    String getGcpTempLocation();
}
