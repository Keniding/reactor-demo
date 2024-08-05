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
        personas.add(new Persona(4, "Reim", 25));
        personas.add(new Persona(4, "Reim", 25));

        return Flux.fromIterable(personas);
    }

    public Flux<Persona> kids() {
        List<Persona> kits = new ArrayList<>();
        kits.add(new Persona(10, "John", 5));
        kits.add(new Persona(20, "Angel", 6));
        kits.add(new Persona(30, "Gia", 7));
        kits.add(new Persona(40, "Gael", 5));
        kits.add(new Persona(50, "Mary", 5));

        return Flux.fromIterable(kits);
    }
}
