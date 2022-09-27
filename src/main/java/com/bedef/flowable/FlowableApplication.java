package com.bedef.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(proxyBeanMethods = false)
public class FlowableApplication{

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	TaskService taskService;

	@Autowired
	RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(FlowableApplication.class, args);
	}

//	@Override
//	public void run(String... strings){
//		System.out.println("Number of process definitions : "
//				+ repositoryService.createProcessDefinitionQuery().count());
//		System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//		runtimeService.startProcessInstanceByKey("bedefRequest");
//		System.out.println("Number of tasks after process start: "
//				+ taskService.createTaskQuery().count());
//	}
}
