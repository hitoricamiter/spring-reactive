package ru.zaikin.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

@SpringBootTest
public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("java")
                .then(Mono.error(new RuntimeException("oops")))
                .log();
        monoString.subscribe(System.out::println, e -> System.out.println("сюда смотри: " + e));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxFluxString = Flux.just("java", "python", "javascript", "c++")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exp occured in FLux")))
                .log();
        fluxFluxString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

}
