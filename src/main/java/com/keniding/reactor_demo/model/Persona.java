package com.keniding.reactor_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private Integer id;
    private String name;
    private Integer edad;

    public Flux<Persona> personas() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Henry", 20));
        personas.add(new Persona(2, "Keniding", 21));
        personas.add(new Persona(3, "Adams", 21));

        return Flux.fromIterable(personas);
    }
}
