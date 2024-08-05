package com.keniding.reactor_demo.operation.filter;

import com.keniding.reactor_demo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filter {

    private static final Logger log = LoggerFactory.getLogger(Filter.class);

    private final Persona p = new Persona();

    public void filter(int mayorQue) {
        p.personas()
                .filter(p -> p.getEdad() > mayorQue)
                .subscribe(x -> log.info("[Filter Flux Filter EdadPersona MayorQue] PersonaEdad {} > {}:", x, mayorQue));
    }

    public void distinct() {
        p.personas()
                .distinct()
                .subscribe(x -> log.info("[Filter Flux Distinct] Persona {}:", x));
    }

    public void take(int nFirst) {
        p.personas()
                .take(nFirst)
                .subscribe(x -> log.info("[Filter Flux Take] Take n first Persona {}:", x));
    }

    public void takeLast(int nLast) {
        p.personas()
                .takeLast(nLast)
                .subscribe(x -> log.info("[Filter Flux TakeLast] Take n last Persona {}:", x));
    }

    public void skip(int nFSkip) {
        p.personas()
                .skip(nFSkip)
                .subscribe(x -> log.info("[Filter Flux Skip] Skip n Persona {}:", x));
    }

    public void skipLast(int nLSkip) {
        p.personas()
                .skipLast(nLSkip)
                .subscribe(x -> log.info("[Filter Flux SkipLast] Skip Last n Persona {}:", x));
    }
}
