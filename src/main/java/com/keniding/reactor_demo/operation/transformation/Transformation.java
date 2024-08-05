package com.keniding.reactor_demo.operation.transformation;

import com.keniding.reactor_demo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Transformation {

    private static final Logger log = LoggerFactory.getLogger(Transformation.class);

    private final Persona p = new Persona();

    public void map(int edadMas) {
        p.personas()
                .map(p -> {
                    p.setEdad(p.getEdad() + edadMas);
                    return p;
                })
                .subscribe(p -> log.info("[Transform Flux Map] Personas Edad + 10: {}", p.toString()));
    }

    public void mapInteger(int start, int count, int masNumber) {
        Flux<Integer> fx = Flux.range(start, count);
        Flux<Integer> fxNew = fx.map(z -> z + masNumber);
        fxNew.subscribe(x -> log.info("[Transform Flux Map Number] Number + {} = {}", masNumber, x));
    }

    public void flatMap(int edadMas) {
        p.personas()
                .flatMap(p ->{
                    p.setEdad(p.getEdad() + edadMas);
                    return Mono.just(p);
                })
                .subscribe(x -> log.info("[Transform Flux FlatMap Persona Return List Mono] PersonaEdad: {}", x));
    }

    public void groupBy() {
        p.personas()
                .groupBy(Persona::getEdad)
                .flatMap(Flux::collectList)
                .subscribe(x -> log.info("[Transform Flux GroupBy EdadPersona FlatMap] PersonaEdad: {}", x));
    }
}
