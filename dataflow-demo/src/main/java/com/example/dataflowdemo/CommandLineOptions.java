package com.example.dataflowdemo;

import org.apache.beam.sdk.options.PipelineOptions;
import org.springframework.core.env.Environment;

public interface CommandLineOptions extends PipelineOptions {
    String getInputFile();
    void setInputFile(String value);

    String getOutputFile();
    void setOutputFile(String value);

    public static void updatePipelineOptions(PipelineOptions options, Environment environment){

    }
}
