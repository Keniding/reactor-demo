package com.keniding.reactor_demo.operation.error;

import com.keniding.reactor_demo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Error {

    private static final Logger log = LoggerFactory.getLogger(Error.class);

    private final Persona p = new Persona();

    //error force
    public void retry(int nLoop) {
        p.personas()
                .concatWith(Flux.error(new RuntimeException("A ERROR")))
                .retry(nLoop)
                .doOnNext(x -> log.info("[Error Flux Retry] Error {}:", x))
                .subscribe();
    }

    public void errorReturn() {
        p.personas()
                .concatWith(Flux.error((new RuntimeException("A ERROR"))))
                .onErrorReturn(new Persona(0, "Error", 0))
                .subscribe(x -> log.error("[Error Flux Return] Personas {}:", x));
    }

    public void errorResume() {
        p.personas()
                .concatWith(Flux.error((new RuntimeException("A ERROR"))))
                .onErrorResume(
                        e -> Mono.just(new Persona(0, "Error", 0))
                        .doOnNext(x -> log.error("[Error Flux Resume] Personas {}:", x))
                )
                .subscribe(x -> log.info("[Error Flux Resume] Personas {}:", x));
    }

    public void errorMap() {
        p.personas()
                .concatWith(Flux.error(new RuntimeException("A ERROR")))
                .onErrorMap(
                        e -> new InterruptedException(e.getMessage())
                )
                .subscribe(x -> log.info("[Error Flux Map] Personas {}", x));
    }
}
