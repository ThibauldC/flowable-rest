package com.bedef.flowable.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskList {

    private List<PersonInfo> tasks;
}
