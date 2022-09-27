package com.bedef.flowable.rest;

import com.bedef.flowable.PersonInfo;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlowableController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @PostMapping(value = "/tasks/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertTask(@RequestBody PersonInfo info){
        runtimeService.startProcessInstanceByKey("bedefRequest");
        System.out.println(info);
        return ResponseEntity.ok("Correct POST");
    }

    @GetMapping(value= "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTasks(@RequestParam String assignee){
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list()
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList());
    }
}
