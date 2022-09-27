package com.bedef.flowable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {
    String name;
    String milCode;
    String unit;
    int age;
}