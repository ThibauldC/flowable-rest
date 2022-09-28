package com.bedef.flowable.rest;

import com.bedef.flowable.domain.PersonInfo;
import com.bedef.flowable.domain.TaskEvaluation;
import com.bedef.flowable.domain.TaskInfo;
import com.bedef.flowable.domain.TaskList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class FlowableController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/tasks/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertTask(@RequestBody PersonInfo info){
        final Map<String, Object> variables = mapper.convertValue(info, Map.class);
        runtimeService.startProcessInstanceByKey("bedefRequest", variables);
        System.out.println(variables);
        return ResponseEntity.ok(String.format("INSERT task succeeded for person with id %s", info.getMilCode()));
    }

    @GetMapping(value= "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskList getTasks(@RequestParam String assignee){
        List<String> taskIdList = taskService.createTaskQuery()
                .taskCandidateGroup(assignee)
                .active()
                .list()
                .stream()
                .map(Task::getId)
                .toList();

        List<TaskInfo> tasks = taskIdList.stream()
                .map(id -> TaskInfo.builder()
                        .id(id)
                        .info(mapper.convertValue(taskService.getVariables(id),PersonInfo.class))
                        .build())
                .toList();

        return new TaskList(tasks);
    }

    @PostMapping(value= "/task/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> evaluateTask(@RequestBody TaskEvaluation eval){

        taskService.complete(eval.getId(), Collections.singletonMap("approved", eval.isApproved()));

        return ResponseEntity.ok("Task successfully evaluated");
    }
}
