package com.keniding.reactor_demo;

import io.reactivex.Observable;
import model.Persona;
import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactorDemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ReactorDemoApplication.class);

	public void reactor() {
		Mono.just(new Persona(1, "Keniding", 21))
				.subscribe(p -> log.info("[Reactor] Persona: {}", p));
	}

	public void rxjava2() {
		Observable.just(new Persona(1, "Keniding", 21))
				.subscribe(p -> log.info("[RxJava2] Persona: {}", p));
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactorDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactor();
		rxjava2();
	}
}
