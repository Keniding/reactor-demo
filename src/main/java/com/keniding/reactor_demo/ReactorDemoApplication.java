package com.keniding.reactor_demo;

import io.reactivex.Observable;
import model.Persona;
import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactorDemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ReactorDemoApplication.class);

	public void reactor() {
		Mono.just(new Persona(1, "Keniding", 21))
				.doOnNext(p -> {
					log.info("[Reactor] Persona: {}", p);
					log.info("Use doOnNext");
				})
				.subscribe();
				//.subscribe(p -> log.info("[Reactor] Persona: {}", p));
	}

	public void rxjava2() {
		Observable.just(new Persona(1, "Keniding", 21))
				.doOnNext(p -> log.info("[RxJava2] Persona: {}", p))
				.subscribe();
				//.subscribe(p -> log.info("[RxJava2] Persona: {}", p));
	}

	public void mono() {
		Mono.just(new Persona(1, "Keniding", 21))
				.subscribe(p -> log.info("[Mono] Persona: {}", p.toString()));
	}

	public void flux() {
		personas().subscribe(p -> log.info("[Flux] Personas, persona: {}", p));
	}

	public void fluxMono() {
		Flux<Persona> fx = personas();
		fx.collectList().subscribe(lista -> log.info("[Flux a Mono] Lista: {}", lista.toString()));
	}

	private Flux<Persona> personas() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Henry", 20));
		personas.add(new Persona(2, "Keniding", 21));
		personas.add(new Persona(3, "Adams", 23));

		return Flux.fromIterable(personas);
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactorDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactor();
		rxjava2();
		mono();
		flux();
		fluxMono();
	}
}
