package com.keniding.reactor_demo.operation.creation;

import com.keniding.reactor_demo.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creation {
    private static final Logger log = LoggerFactory.getLogger(Creation.class);

    public void justFrom() {
        Mono.just(new Persona(1, "Keniding", 21));
        /*
        Flux.fromIterable(collection);
        Observable.just(item);
        */
    }

    //Para Flujos vacios
    public void empty() {
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    public void range(int start, int count) {
        Flux.range(start, count)
                .doOnNext(i -> log.info("[Create Flux Range] Valor de i: {}", i))
                .subscribe();
    }

    public void repeat(int numRepeat) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Henry", 20));
        personas.add(new Persona(2, "Keniding", 21));
        personas.add(new Persona(3, "Adams", 23));

        /*
        Mono.just(personas)
                .repeat(numRepeat)
                .subscribe(p -> log.info("[Create Mono Repeat] Persona: {}", p));
        */

        Flux.fromIterable(personas)
                .repeat(numRepeat)
                .subscribe(p -> log.info("[Create Flux Repeat] Persona: {}", p));
    }
}
