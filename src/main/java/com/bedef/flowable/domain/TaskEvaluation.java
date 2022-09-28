package com.bedef.flowable.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskEvaluation {
    private String id;
    private boolean approved;
}
