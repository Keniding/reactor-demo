package com.keniding.reactor_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Persona {
    private Integer id;
    private String name;
    private Integer edad;
}
