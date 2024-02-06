package com.midas.app;

import com.midas.app.activities.AccountActivity;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.midas.app.workflows.CreateAccountWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = SpringApplicationAdminJmxAutoConfiguration.class)
public class MidasApplication {

  public static void main(String[] args) {
    SpringApplication.run(MidasApplication.class, args);
    ConfigurableApplicationContext configurableApplicationContext =
        SpringApplication.run(MidasApplication.class);
    WorkerFactory workerFactory = configurableApplicationContext.getBean(WorkerFactory.class);
    AccountActivity activity = configurableApplicationContext.getBean(AccountActivity.class);
    Worker worker = workerFactory.newWorker(CreateAccountWorkflow.QUEUE_NAME);
    worker.registerWorkflowImplementationTypes(CreateAccountWorkflowImpl.class);
    worker.registerActivitiesImplementations(activity);
    workerFactory.start();
  }
}
