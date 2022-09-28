package com.bedef.flowable.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class TaskInfo {
    String id;
    PersonInfo info;
}
