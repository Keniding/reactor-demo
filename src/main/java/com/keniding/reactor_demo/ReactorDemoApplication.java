package com.keniding.reactor_demo;

import com.keniding.reactor_demo.operation.creation.Creation;
import com.keniding.reactor_demo.operation.filter.Filter;
import com.keniding.reactor_demo.operation.transformation.Transformation;
import io.reactivex.Observable;
import com.keniding.reactor_demo.model.Persona;
import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactorDemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ReactorDemoApplication.class);

	Persona p = new Persona();

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
		p.personas().subscribe(p -> log.info("[Flux] Personas, persona: {}", p));
	}

	public void fluxMono() {
		Flux<Persona> fx = p.personas();
		fx.collectList().subscribe(lista -> log.info("[Flux a Mono] Lista: {}", lista.toString()));
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

		Creation appC = new Creation();
		appC.range(0,5);
		appC.repeat(2);

		Transformation appT = new Transformation();
		appT.map(10);
		appT.mapInteger(3, 7, 10);
		appT.flatMap(12);
		appT.groupBy();

		Filter appF = new Filter();
		appF.filter(20);
		appF.distinct();
		appF.take(2);
		appF.takeLast(3);
		appF.skip(2);
		appF.skipLast(2);
	}
}
