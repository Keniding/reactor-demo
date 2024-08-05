package com.keniding.reactor_demo.operation.combination;

import com.keniding.reactor_demo.model.Persona;
import com.keniding.reactor_demo.model.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Combination {

    private static final Logger log = LoggerFactory.getLogger(Combination.class);

    private final Persona p = new Persona();

    private final Sale s = new Sale();

    Flux<Persona> fx1 = p.personas();
    Flux<Persona> fx2 = p.kids();
    Flux<Sale> fx3 = s.sales();

    public void merge() {
        Flux.merge(fx1, fx2, fx3)
                .subscribe(x -> log.info("[Combination Flux Merge] Merge Persona(x, kids) and Sales {}:", x));
    }

    public void zip() {
        Flux.zip(fx1, fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2))
                .subscribe(x -> log.info("[Combination Flux Zip] Zip Persona(x, kids) {}:", x));

        Flux.zip(fx1, fx2, fx3)
                .subscribe(x -> log.info("[Combination Flux Zip] Zip {}:", x));
    }

    public void zipWith() {
        fx2.zipWith(fx3, (p2, s1) -> String.format("Flux1: %s, Flux2: %s", p2, s1))
                .subscribe(x -> log.info("[Combination Flux ZipWith] Zip Persona(kid)WithSales {}:", x));
    }
}
