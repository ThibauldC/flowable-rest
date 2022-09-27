package com.bedef.flowable.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.flowable.task.api.Task;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskList {

    private List<String> tasks;
}
