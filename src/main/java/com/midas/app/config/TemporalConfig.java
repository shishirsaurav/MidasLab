package com.midas.app.config;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class TemporalConfig {

  String temporalServiceAddress = "localhost:8233";

  @Bean
  public WorkflowServiceStubs workflowServiceStubs() {
    return WorkflowServiceStubs.newInstance(
        WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServiceAddress).build());
  }

  @Bean
  public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
    return WorkflowClient.newInstance(
        workflowServiceStubs, WorkflowClientOptions.newBuilder().setNamespace("default").build());
  }

  @Bean
  public WorkerFactory workerFactory(WorkflowClient workflowClient) throws ClassNotFoundException {
    return WorkerFactory.newInstance(workflowClient);
  }
}
